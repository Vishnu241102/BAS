package website.BAS.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
@Autowired
    private MongoTemplate mongoTemplate;


    public List<user> getAllItems(String type) {
        Query query;

        if ("lost".equals(type)) {
            query = new Query(Criteria.where("type").is("lost").and("acceptedTo").is(""));
        } else if ("found".equals(type)) {
            query = new Query(Criteria.where("type").is("found").and("acceptedTo").is(""));
        } else {
            query = new Query(Criteria.where("type").is("sell").and("acceptedTo").is(""));
        }

        query.with(Sort.by(Sort.Order.desc("date"))); // Sort by date in descending order

        List<user> users = mongoTemplate.find(query, user.class);

        return users;
    }
    public user saveUser(user user) {
        return mongoTemplate.save(user);
    }

    public user addToWishlist(String id, String email, boolean addToWishlist) {
        Query query = new Query(Criteria.where("_id").is(id));
        user user = mongoTemplate.findOne(query, user.class);

        if (user != null) {
            Update update = new Update();
            if (addToWishlist) {
                update.addToSet("wishlist", email);
            } else {
                update.pull("wishlist", email);
            }

            return mongoTemplate.findAndModify(query, update, user.class);
        } else {
            // Handle the case where the user with the given ID is not found
            return null;
        }
    }

    public List<user> getSellItems(String search, String sort, String category) {
        Query query = new Query();

        if (search != null && !search.isEmpty()) {
            Criteria regexCriteria = Criteria.where("product").regex(search, "i");
            query.addCriteria(regexCriteria);
        }

        if (category != null && !category.isEmpty() && !"all".equals(category)) {
            query.addCriteria(Criteria.where("category").is(category));
        }

        if ("latest".equals(sort)) {
            query.with(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Order.desc("date")));
        } else if ("pHtL".equals(sort)) {
            query.with(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Order.desc("sPrice")));
        } else if ("pLtH".equals(sort)) {
            query.with(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Order.asc("sPrice")));
        }

        query.addCriteria(Criteria.where("type").is("sell").and("acceptedTo").is(""));

        return mongoTemplate.find(query, user.class);
    }

    public List<user> getFoundItems(String search, String sort, String category) {
        Query query = new Query();

        if (search != null && !search.isEmpty()) {
            Criteria regexCriteria = Criteria.where("product").regex(search, "i");
            query.addCriteria(regexCriteria);
        }

        if (category != null && !category.isEmpty() && !"all".equals(category)) {
            query.addCriteria(Criteria.where("category").is(category));
        }

        if ("latest".equals(sort)) {
            query.with(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Order.desc("date")));
        }

        query.addCriteria(Criteria.where("type").is("found").and("acceptedTo").is(""));

        return mongoTemplate.find(query, user.class);
    }

    public user buyThis(String id, String email) {
        Query query = new Query(Criteria.where("_id").is(id).and("customers").in(email));
        user user = mongoTemplate.findOne(query, user.class);

        if (user != null) {
            // Email already present in the customers array
            System.out.println("Email already exists");
            return null; // Handle the response accordingly
        } else {
            Update update = new Update().addToSet("customers", email);
            return mongoTemplate.findAndModify(query, update, user.class);
        }
    }

    public List<user> getWishlist(String email, String type) {
        Query query = new Query(Criteria.where("wishlist").in(email).and("type").is(type).and("acceptedTo").is(""));

        if ("lost".equals(type) || "found".equals(type) || "sell".equals(type)) {
            return mongoTemplate.find(query, user.class);
        }

        return null; // Handle invalid type
    }

    public List<user> getYourUploads(String email) {
        Query query = new Query(Criteria.where("email").is(email)).with(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Order.desc("date")));
        return mongoTemplate.find(query, user.class);
    }
    public user deleteThis(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findAndRemove(query, user.class);
    }

    public user accept(String id, String accept) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("acceptedTo", accept);
        return mongoTemplate.findAndModify(query, update, user.class);
    }

    public List<user> getYourOrders(String email) {
        Query query = new Query(Criteria.where("customers").in(email)).with(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Order.desc("date")));
        return mongoTemplate.find(query, user.class);
    }

    public user cancelOrder(String id, String email) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().pull("customers", email);
        return mongoTemplate.findAndModify(query, update, user.class);
    }
}
