package models;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class Comment extends Model
{
  public String commentText;
  public Long postid;
  public String date;
  
  @ManyToOne
  public User from;


  public Comment(User from, String commentText, Long postid, String date){
    this.from = from;
    this.commentText = commentText;
    this.postid = postid;
    this.date = date;
  }
}