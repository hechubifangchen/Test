import com.databps.bigdaf.chiwen.exceptions.ChiWenAccessControlException;
import com.databps.bigdaf.chiwen.hdfsagent.ChiWenFSPermissionChecker;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.hdfs.server.namenode.INode;
import org.apache.hadoop.security.UserGroupInformation;
import org.junit.Before;
import org.junit.Test;

/**
 * TestChiWenFSPermissionChecker
 *
 * @author lgc
 * @create 2017-07-26 下午5:05
 */
public class TestChiWenFSPermissionChecker {

  UserGroupInformation ugi;
  INode inode;
  FsAction access;

  @Before
  public void setUp() {

  }


  @Test
  public void TestcheckPermissionPre() {
    ChiWenFSPermissionChecker.checkPermissionPre("/usr/local/hadoop");
  }

  @Test
  public void TestcheckPermissionPost() {
    ChiWenFSPermissionChecker.checkPermissionPost("/usr/local/hadoop");
    for (int i = 0; i < 10; i++) {
      try {
        System.out.println("This is TestcheckPermission running!");
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void testAuthorizeAccessForUser() {
    try {
      ChiWenFSPermissionChecker
          .AuthorizeAccessForUser("/tmp", "leonado", FsAction.READ_WRITE, "leonado",
              new HashSet<String>() {{
                add("aaagroup");
                add("supergroup");
              }});
    } catch (ChiWenAccessControlException e) {
      e.printStackTrace();
    }
    while (true){
      try {
        System.out.println("This is TestcheckPermission running!");
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

//  @Test
//  public void testAuthorizeAccessForUserMultiThread() {
//    try {
//      ChiWenFSPermissionChecker
//          .AuthorizeAccessForUser("/tmp", "leonado", FsAction.READ_WRITE, "leonado",
//              new HashSet<String>() {{
//                add("aaagroup");
//                add("supergroup");
//              }});
//    } catch (ChiWenAccessControlException e) {
//      e.printStackTrace();
//    }
//    while (true){
//      try {
//        System.out.println("This is TestcheckPermission running!");
//        Thread.sleep(2000);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    }
//  }
}