import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import play.*;
import play.db.jpa.Blob;
import play.jobs.*;
import play.libs.MimeTypes;
import play.test.*;
 
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job 
{ 
  public void doJob() throws FileNotFoundException
  {
    if (User.count() == 0)
    {
      Fixtures.loadModels("data.yml");
    }
    
  String photoName = "../public/images/homer.gif";
  Blob blob = new Blob ();
  blob.set(new FileInputStream(photoName), MimeTypes.getContentType(photoName));
  User homer = User.findByEmail("homer@simpson.com");
  homer.profilePicture = blob;
  
  String photoName2 = "../public/images/marge.gif";
  Blob blob2 = new Blob ();
  blob2.set(new FileInputStream(photoName2), MimeTypes.getContentType(photoName2));
  User marge = User.findByEmail("marge@simpson.com");
  marge.profilePicture = blob2;
  
  String photoName3 = "../public/images/bart.gif";
  Blob blob3 = new Blob ();
  blob3.set(new FileInputStream(photoName3), MimeTypes.getContentType(photoName3));
  User bart = User.findByEmail("bart@simpson.com");
  bart.profilePicture = blob3;
  
  String photoName4 = "../public/images/lisa.gif";
  Blob blob4 = new Blob ();
  blob4.set(new FileInputStream(photoName4), MimeTypes.getContentType(photoName4));
  User lisa = User.findByEmail("lisa@simpson.com");
  lisa.profilePicture = blob4;
  
  String photoName5 = "../public/images/maggie.gif";
  Blob blob5 = new Blob ();
  blob5.set(new FileInputStream(photoName5), MimeTypes.getContentType(photoName5));
  User maggie = User.findByEmail("maggie@simpson.com");
  maggie.profilePicture = blob5;
}
  }
  
