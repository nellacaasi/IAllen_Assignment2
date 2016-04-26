package models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Post extends Model
{
  public String title;
  @Lob
  public String content;

  public Post(String title, String content)
  {
    this.title = title;
    this.content = content;
  }
  
  @OneToMany(mappedBy = "postid")
  public List<Comment> comments = new ArrayList<Comment>();

  public String toString()
  {
    return title;
  } 
}