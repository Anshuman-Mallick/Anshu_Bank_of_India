package com.bank.abibank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bank.abibank.model.User;
import com.bank.abibank.repository.UserRepository;
import com.bank.abibank.model.Transaction;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import com.bank.abibank.jwt.JwtUtil;
import com.bank.abibank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository repo;

    @Value("${ADMIN_USERNAME}")
    private String adminUsername;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private TransactionRepository transactionRepo;

    @GetMapping("/register")
    public String test() {
        return "Register API Working";
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        user.setUserId("ABI" + (int) (Math.random() * 10000));
        user.setPassword("Temp@" + (int) (Math.random() * 1000));

        return repo.save(user);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User loginUser) {

        Map<String, Object> response = new HashMap<>();

        // Find user only by User ID
        User user = repo.findByUserId(loginUser.getUserId());
        //System.out.println("User Found: " + user);
        if (user == null) {
            response.put("message", "Invalid User ID");
            response.put("token", null);
            return response;
        }

        // Check approval
        if (!user.isApproved()) {
            response.put("message", "Account Pending For Approval");
            response.put("token", null);
            return response;
        }

        // Check password
        if (!user.getPassword().equals(loginUser.getPassword())) {
            response.put("message", "Invalid Password");
            response.put("token", null);
            return response;
        }

        // Login success
        String token = jwtUtil.generateToken(user.getUserId());

        response.put("message", "Login Success");
        response.put("token", token);
        response.put("user", user);

        return response;
    }


    // DASHBOARD API
    @GetMapping("/dashboard/{userId}")
    public User dashboard(
            @PathVariable String userId
    ) {

        return repo.findByUserId(userId);
    }

    @PutMapping("/addMoney/{userId}/{amount}")
    public User addMoney(

            @PathVariable String userId,

            @PathVariable double amount
    ) {

        // FIND USER
        User user = repo.findByUserId(userId);

        // ADD BALANCE
        user.setBalance(
                user.getBalance() + amount
        );

        // SAVE USER
        repo.save(user);

        // CREATE TRANSACTION
        Transaction transaction =
                new Transaction();

        transaction.setSenderId("BANK");

        transaction.setReceiverId(userId);

        transaction.setAmount(amount);

        transaction.setType("Money Added");

        transaction.setDate(
                java.time.LocalDate.now().toString()
        );

        transaction.setStatus("Success");

        // SAVE TRANSACTION
        transactionRepo.save(transaction);

        return user;
    }

    @PostMapping("/sendMoney")
    public String sendMoney(@RequestBody Transaction transaction) {

        // FIND USERS
        User sender = repo.findByUserId(
                transaction.getSenderId()
        );

        User receiver = repo.findByUserId(
                transaction.getReceiverId()
        );

        // CHECK USERS
        if (sender == null || receiver == null) {

            return "Invalid User ID";
        }

        // CHECK BALANCE
        if (sender.getBalance() < transaction.getAmount()) {

            return "Insufficient Balance";
        }

        // DEDUCT MONEY
        sender.setBalance(
                sender.getBalance() - transaction.getAmount()
        );

        // ADD MONEY
        receiver.setBalance(
                receiver.getBalance() + transaction.getAmount()
        );

        // SAVE USERS
        repo.save(sender);
        repo.save(receiver);
        // SAVE TRANSACTION
        transaction.setType("Money Transfer");
        transaction.setStatus("Success");
        transaction.setDate(java.time.LocalDate.now().toString());

        transactionRepo.save(transaction);

        return "Money Sent Successfully";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(@RequestBody User admin){

        if(admin.getUserId().equals(adminUsername) &&
                admin.getPassword().equals(adminPassword)){

            return "Admin Success";
        }

        return "Invalid Admin";
    }
    @GetMapping("/allUsers")
    public List<User> getAllUsers() {

        return repo.findAll();
    }

    @GetMapping("/admin/stats")
    public Map<String, Object> adminStats() {

        Map<String, Object> data =
                new HashMap<>();

        // TOTAL USERS
        long totalUsers = repo.count();

        // TOTAL BALANCE
        double totalBalance = 0;

        List<User> users = repo.findAll();

        for (User user : users) {

            totalBalance += user.getBalance();
        }

        // TOTAL TRANSACTIONS
        long totalTransactions =
                transactionRepo.count();

        data.put("totalUsers", totalUsers);

        data.put("totalBalance", totalBalance);

        data.put(
                "totalTransactions",
                totalTransactions
        );

        data.put("users", users);

        return data;
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {

        repo.deleteById(id);

        return "User Deleted";
    }

    @GetMapping("/totalBalance")
    public double totalBalance() {

        List<User> users = repo.findAll();

        double total = 0;

        for (User user : users) {
            total += user.getBalance();
        }

        return total;
    }

    @GetMapping("/totalUsers")
    public long totalUsers() {

        return repo.count();
    }

    @GetMapping("/totalTransactions")
    public long totalTransactions() {

        return transactionRepo.count();
    }

    @GetMapping("/transactions/{userId}")
    public List<Transaction> getTransactions(@PathVariable String userId) {

        return transactionRepo.findBySenderIdOrReceiverId(userId, userId);
    }

@PutMapping("/changePhoto")
public String changePhoto(@RequestBody User user) {

    User dbUser = repo.findByUserId(user.getUserId());

    if (dbUser == null) {
        return "User Not Found";
    }

    dbUser.setProfilePhoto(user.getProfilePhoto());

    repo.save(dbUser);

    return "Profile Photo Updated Successfully";
}

    @PutMapping("/updateProfile")
    public String updateProfile(@RequestBody User user) {

        User dbUser = repo.findByUserId(user.getUserId());

        if(dbUser == null){
            return "User Not Found";
        }

        dbUser.setFullName(user.getFullName());
        dbUser.setMobile(user.getMobile());
        dbUser.setEmail(user.getEmail());
        dbUser.setAadhaar(user.getAadhaar());
        dbUser.setPan(user.getPan());
        dbUser.setAddress(user.getAddress());

        repo.save(dbUser);

        return "Profile Updated Successfully";
    }

    @PutMapping("/forgotPassword")
    public String forgotPassword(@RequestBody User user){

        User dbUser = repo.findByUserId(user.getUserId());

        if(dbUser == null){

            return "User Not Found";
        }

        if(!dbUser.getMobile().equals(user.getMobile())){

            return "Mobile Number Incorrect";
        }

        dbUser.setPassword(user.getPassword());

        repo.save(dbUser);

        return "Password Updated Successfully";
    }

    @PutMapping("/approveUser/{userId}")
    public String approveUser(
            @PathVariable String userId){

        User user = repo.findByUserId(userId);

        if(user==null){

            return "User Not Found";
        }

        user.setApproved(true);

        repo.save(user);

        return "Customer Approved Successfully";
    }
}
