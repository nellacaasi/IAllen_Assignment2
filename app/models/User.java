package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import play.db.jpa.Model;
import play.Logger;
import play.db.jpa.Blob;


@Entity
@Table(name="`User`") //This is necessary because User is a reserved word in PostGreSQL
public class User extends Model
{
  public String firstName;
  public String lastName;
  public String email;
  public String password;
  public String statusText;
  public Blob   profilePicture;
  public int    age;
  public String nationality;
  public boolean userOnline;
  public boolean leaveComment;

  @OneToMany(mappedBy = "sourceUser")
  public List<Friendship> friendships = new ArrayList<Friendship>();
  
  @OneToMany(mappedBy = "to")
  public List<Message> inbox = new ArrayList<Message>();
  
  @OneToMany(mappedBy = "from")
  public List<Message> outbox = new ArrayList<Message>();
  
  @OneToMany
  public List<Post> posts = new ArrayList<Post>();
  
  
  public User(String firstName, String lastName, String email, String password, int age, String nationality)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.age = age;
    this.nationality = nationality;
  }
  
  public static User findByEmail(String email)
  {
    return find("email", email).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }  
  
  public void befriend(User friend)
  {
    Friendship friendship = new Friendship(this, friend);
    friendships.add(friendship);
    friendship.save();
    save();
  }

  public void unfriend(User friend)
  {
    Friendship thisFriendship = null;
    
    for (Friendship friendship:friendships)
    {
      if (friendship.targetUser== friend)
      {
        thisFriendship = friendship;
      }
    }
    friendships.remove(thisFriendship);
    thisFriendship.delete();
    save();
  }  
  
  public void sendMessage (User to, String messageText){
    Message message = new Message (this, to, messageText);
    outbox.add(message);
    to.inbox.add(message);
    message.save();
  }  
  
  public void sendComment (User to, String commentText, Long postid, String date)
  {
	User user = to;
	Post post = Post.findById(postid);
	
    Comment comment = new Comment (this, commentText,postid, date);
    post.comments.add(comment);
    comment.save();
    Logger.info("on post: "+postid+" to user: "+user);
  }
}