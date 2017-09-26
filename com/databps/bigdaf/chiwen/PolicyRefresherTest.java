package com.databps.bigdaf.chiwen;

import com.databps.bigdaf.chiwen.adminclient.ChiWenAdminClient;
import com.databps.bigdaf.chiwen.adminclient.ChiWenAdminRESTClient;
import com.databps.bigdaf.chiwen.model.ChiWenPolicy;
import com.databps.bigdaf.chiwen.policy.ChiWenPolicyEngine;
import com.databps.bigdaf.chiwen.policy.ChiWenPolicyEngineImpl;
import com.databps.bigdaf.chiwen.policy.PolicyRefresher;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Before;
import org.junit.Test;

/**
 * TestPolicyRefresher
 *
 * @author lgc
 * @create 2017-07-22 下午3:07
 */
public class PolicyRefresherTest {

    Class<?> policyRefresherClass = null;
    Class<?> policyEngineClass = null;
    Method loadFromCacheMethod = null;
    Method saveToCache = null;
    PolicyRefresher policyRefresher = null;
    private Gson gson = null;
    ChiWenPolicyEngine policyEngine =null;


    @Before
    public void setup() {
        try {
            policyRefresherClass = Class.forName("com.databps.bigdaf.chiwen.policy.PolicyRefresher");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            policyEngineClass = Class.forName("com.databps.bigdaf.chiwen.policy.ChiWenPolicyEngineImpl");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Class[] params = new Class[]{};
        try {
            loadFromCacheMethod = policyRefresherClass.getDeclaredMethod("loadFromCache", params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        loadFromCacheMethod.setAccessible(true);
        Class[] paramsForSTCache = new Class[]{ChiWenPolicy.class};

        try {
            saveToCache = policyRefresherClass.getDeclaredMethod("saveToCache", paramsForSTCache);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        saveToCache.setAccessible(true);


        this.gson = new GsonBuilder().setDateFormat("yyyyMMdd-HH:mm:ss.SSS-Z").setPrettyPrinting()
                .create();

        ChiWenPolicyEngine policyEngine = new ChiWenPolicyEngineImpl();
        String serviceType = "hdfs";
        String serviceName = "hdfs";
        String policyName = "hdfs";
        String cacheDir = "F:/test/testfile";
        ChiWenAdminClient chiWenAdmin = new ChiWenAdminRESTClient();
        policyRefresher = new PolicyRefresher(policyEngine, serviceType, serviceName, policyName,
                chiWenAdmin, 30 * 1000, cacheDir);
    }


    @Test
    public void testLoadFromCache() {
        try {
            loadFromCacheMethod.invoke(policyRefresher, null);
            try {
                Field policyEngineField = policyRefresherClass.getDeclaredField("policyEngine");
                policyEngineField.setAccessible(true);
                policyEngine = (ChiWenPolicyEngine) policyEngineField.get(policyRefresher);
                Field policy = policyEngineClass.getDeclaredField("policy");
                policy.setAccessible(true);

                ChiWenPolicy policyInstance = (ChiWenPolicy) policy.get(policyEngine);
                System.out.println("QQQQQQQQ" + gson.toJson(policyInstance).toString());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void testSaveToCache() {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setPrettyPrinting();
//        Gson gson1 = gsonBuilder.create();


//        System.out.printf(gson1.toString());
        String jsonTest = "{\n" +
                "\"pluginUid\":\"0d047247-bafe-4cf8-8e9b-d5d377284b2d\",\n" +
                "\"lastVersion\":24,\n" +
                "\"updateTime\":\"20160530-19:34:17.000-+0800\",\n" +
                "\"isEnabled\":true,\n" +
                "\"privileges\":[\n" +
                "{\n" +
                "\"name\":\"TmpdirWrite\",\n" +
                "\"resources\":{\n" +
                "\"path\":{\n" +
                "\"values\":[\n" +
                "\"tmptmpdir\"\n" +
                "]\n" +
                "}\n" +
                "},\n" +
                "\"privilegeItems\":[\n" +
                "{\n" +
                "\"users\":[\n" +
                "\"hive\"\n" +
                "],\n" +
                "\"groups\":[\n" +
                "\"IT\"\n" +
                "],\n" +
                "\"accesses\":[\n" +
                "{\n" +
                "\"type\":\"write\"\n" +
                "}\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "\"users\":[\n" +
                "\"bob\"\n" +
                "],\n" +
                "\"groups\":[\n" +
                "\"1234567890\"\n" +
                "],\n" +
                "\"accesses\":[\n" +
                "{\n" +
                "\"type\":\"write\"\n" +
                "}\n" +
                "]\n" +
                "}\n" +
                "]\n" +
                "}\n" +
                "]\n" +
                "}";
        System.out.print(jsonTest);
        ChiWenPolicy policy = gson.fromJson(jsonTest, ChiWenPolicy.class);
        try {
            saveToCache.invoke(policyRefresher,policy);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}


