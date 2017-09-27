package com.databps.bigdaf.chiwen;

import com.databps.bigdaf.chiwen.util.ChiWenHdfsPluginHeartBeat;
import org.junit.Before;
import org.junit.Test;

public class TestChiwenHdfsPluginHeartBeat {
    ChiWenHdfsPluginHeartBeat chiWenHdfsPluginHeartBeat = null;
@Before
public void setup(){
    this.chiWenHdfsPluginHeartBeat=new ChiWenHdfsPluginHeartBeat();
}
    @Test
    public void testRun(){
        try {
            chiWenHdfsPluginHeartBeat.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testStart(){
        try {
            chiWenHdfsPluginHeartBeat.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
