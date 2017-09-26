package com.databps.bigdaf.chiwen;
import com.databps.bigdaf.chiwen.hdfsagent.ChiWenHdfsAccessRequest;
import com.databps.bigdaf.chiwen.policy.ChiWenPolicyEngineImpl;
import org.apache.hadoop.fs.permission.FsAction;
import org.junit.Before;

/**
 * TestChiWenPolicyEngineImpl
 *
 * @author lgc
 * @create 2017-08-03 下午7:49
 */
public class TestChiWenPolicyEngineImpl {

  ChiWenPolicyEngineImpl chiWenPolicyEngineImpl;
  ChiWenHdfsAccessRequest chiWenHdfsAccessRequest;

  @Before
  public void setUp(){

  this.chiWenPolicyEngineImpl = new ChiWenPolicyEngineImpl();
//  this.chiWenHdfsAccessRequest = new ChiWenHdfsAccessRequest("/temp","leonado", FsAction.ALL, String accessType, String user, Set<String> groups);

  }
}