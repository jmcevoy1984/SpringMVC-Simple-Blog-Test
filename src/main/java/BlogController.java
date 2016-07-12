package blog;

import java.util.ArrayList;
//import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController{

	public ArrayList<Post> allPosts = new ArrayList<Post>();
	
	@RequestMapping(value={"/createPost", "/"}, method=RequestMethod.GET)
	public String viewPosts(Model model){

		//Add a key/value pair to the "model" map (aka: hash, hasmap, dict etc..)
		model.addAttribute("post", new Post());

		//return a html template named posts
		return "addPost";
	}

	@RequestMapping(value="/viewPosts", method=RequestMethod.POST)
	public String createPost(@ModelAttribute Post post, Model model){
		//LocalDateTime currentTime = LocalDateTime.now();
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedDate = currentDate.format(formatter);
		post.setDate(formattedDate);
		allPosts.add(0, post);
		model.addAttribute("posts", allPosts);
		return "posts";
	}

	@RequestMapping(value="/viewPosts", method=RequestMethod.GET)
	public String createPost(Model model){
		model.addAttribute("posts", allPosts);
		return "posts";
	}
	
}