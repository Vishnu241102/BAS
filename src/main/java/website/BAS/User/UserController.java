package website.BAS.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {
@Autowired
private UserService userService;
@GetMapping("/getAll")
public List<user> getStudents(@RequestParam(name = "type") String type){

   return userService.getAllItems(type);
}
   @PostMapping("/getAll")
   public ResponseEntity<user> createItem(@RequestBody user user) {
      try {
         user savedUser = userService.saveUser(user);
         return ResponseEntity.ok(savedUser);
      } catch (Exception e) {
         return ResponseEntity.badRequest().build();
      }
   }

   @PostMapping("/addToWish")
   public ResponseEntity<user> addToWishlist(
           @RequestParam String id,
           @RequestParam String email,
           @RequestParam String flag
   ) {
      try {
         boolean addToWishlist = "no".equals(flag);
         user updatedUser = userService.addToWishlist(id, email, addToWishlist);

         if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
         } else {
            // Handle the case where the user with the given ID is not found
            return ResponseEntity.notFound().build();
         }
      } catch (Exception e) {
         // You may want to customize the exception handling
         return ResponseEntity.badRequest().build();
      }
   }

   @GetMapping("/getSellType")
   public ResponseEntity<List<user>> getSellType(
           @RequestParam(required = false) String search,
           @RequestParam(required = false) String sort,
           @RequestParam(required = false) String category
   ) {
      try {
         List<user> sellItems = userService.getSellItems(search, sort, category);
         return ResponseEntity.ok(sellItems);
      } catch (Exception e) {
         // You may want to customize the exception handling
         return ResponseEntity.badRequest().build();
      }
   }

   @GetMapping("/getFoundType")
   public ResponseEntity<List<user>> getFoundType(
           @RequestParam(required = false) String search,
           @RequestParam(required = false) String sort,
           @RequestParam(required = false) String category
   ) {
      try {
         List<user> foundItems = userService.getFoundItems(search, sort, category);
         return ResponseEntity.ok(foundItems);
      } catch (Exception e) {
         // You may want to customize the exception handling
         return ResponseEntity.badRequest().build();
      }
   }

   @GetMapping("/getWishList")
   public ResponseEntity<List<user>> getWishlist(
           @RequestBody user user,
           @RequestParam String type
   ) {

      try {
         String email = user.getEmail();
         List<user> wishlist = userService.getWishlist(email, type);
         return ResponseEntity.ok(wishlist);
      } catch (Exception e) {
         // You may want to customize the exception handling
         return ResponseEntity.badRequest().build();
      }
   }

   @GetMapping("/yourUploads")
   public ResponseEntity<List<user>> getYourUploads(@RequestBody user user) {

      try {
         String email = user.getEmail();
         List<user> yourUploads = userService.getYourUploads(email);
         return ResponseEntity.ok(yourUploads);
      } catch (Exception e) {
         // You may want to customize the exception handling
         return ResponseEntity.badRequest().build();
      }
   }

   @DeleteMapping("/deleteThis")
   public ResponseEntity<user> deleteThis(@RequestParam String id) {
      try {
         user deletedUser = userService.deleteThis(id);
         if (deletedUser != null) {
            return ResponseEntity.ok(deletedUser);
         } else {
            // Handle the case where the user with the given id was not found
            return ResponseEntity.notFound().build();
         }
      } catch (Exception e) {
         // You may want to customize the exception handling
         return ResponseEntity.badRequest().build();
      }
   }

   @PostMapping("/accept")
   public ResponseEntity<user> accept(@RequestBody user request) {
      try {
         user updatedUser = userService.accept(request.getId(), request.getAcceptedTo());
         if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
         } else {
            // Handle the case where the user with the given id was not found
            return ResponseEntity.notFound().build();
         }
      } catch (Exception e) {
         // You may want to customize the exception handling
         return ResponseEntity.badRequest().build();
      }
   }

   @GetMapping("/yourOrders")
   public ResponseEntity<List<user>> getYourOrders(@RequestBody user user) {
      try {
         String email = user.getEmail();
         List<user> yourOrders = userService.getYourOrders(email);
         return ResponseEntity.ok(yourOrders);
      } catch (Exception e) {
         // You may want to customize the exception handling
         return ResponseEntity.badRequest().build();
      }
   }

   @PostMapping("/cancelOrder")
   public ResponseEntity<user> cancelOrder(@RequestBody user request) {
      try {
         user updatedUser = userService.cancelOrder(request.getId(), request.getEmail());
         if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
         } else {
            // Handle the case where the user with the given id was not found
            return ResponseEntity.notFound().build();
         }
      } catch (Exception e) {
         // You may want to customize the exception handling
         return ResponseEntity.badRequest().build();
      }
   }

   @PostMapping("/buyThis")
   public ResponseEntity<Object> buyThis(@RequestBody user buyRequest) {
      try {
         userService.buyThis(buyRequest.getId(), buyRequest.getEmail());
         return new ResponseEntity<>("Success", HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
}