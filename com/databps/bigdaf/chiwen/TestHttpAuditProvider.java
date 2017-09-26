package com.databps.bigdaf.chiwen;
import com.databps.bigdaf.chiwen.audit.AuthzAuditEvent;
import com.databps.bigdaf.chiwen.audit.HttpAuditProvider;
import com.databps.bigdaf.core.common.AuditStatus;
import com.databps.bigdaf.core.common.AuditType;
import java.util.Date;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;

/**
 * TestHttpAuditProvider
 *
 * @author lgc
 * @create 2017-08-02 上午10:48
 */
public class TestHttpAuditProvider {
  AuthzAuditEvent authzAuditEvent;
  HttpAuditProvider httpAuditProvider;
  @Before
  public void setUp(){
  this.authzAuditEvent=new AuthzAuditEvent();
    authzAuditEvent.setIsAllowed(AuditStatus.FAILURE.getName());
    authzAuditEvent.setAccessTime(new Date());
    authzAuditEvent.setAction("rwx");
    authzAuditEvent.setAccessType("all");
//    authzAuditEvent.setClientType("hdfs");
    authzAuditEvent.setClientIPAddress("123.123.123.123");
    authzAuditEvent.setPath("/usr/local");
    authzAuditEvent.setUserGroups(new HashSet<String>(){{
      add("superuser");
      add("plainuser");
    }});
    authzAuditEvent.setUser("leonado");
    httpAuditProvider=new HttpAuditProvider();

  }

  @Test
  public void log(){

    httpAuditProvider.log(authzAuditEvent);
//    System.out.println(AuditType.HDFS.getName());
  }

  public static void main(String[] args) {
    System.out.println(System.getenv("HOME"));
  }


}