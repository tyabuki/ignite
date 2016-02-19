/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.internal.util.typedef.internal.U;
import org.apache.ignite.testframework.GridTestUtils;

import static org.apache.ignite.cache.CacheAtomicityMode.ATOMIC;

/**
 * Test compute node js.
 */
public class NodeJsComputeSelfTest extends NodeJsAbstractTest {
    /**
     * Constructor.
     */
    public NodeJsComputeSelfTest() {
        super("test-compute.js");
    }

    /** {@inheritDoc} */
    @Override protected void beforeTestsStarted() throws Exception {
        startGrids(2);
    }

    /** {@inheritDoc} */
    @Override protected void afterTestsStopped() throws Exception {
        stopAllGrids();
    }

    /** {@inheritDoc} */
    @Override protected void beforeTest() throws Exception {
        grid(0).cache("mycache").removeAll();
    }

    /** {@inheritDoc} */
    @Override protected CacheConfiguration cacheConfiguration() {
        CacheConfiguration ccfg = new CacheConfiguration();

        ccfg.setName(CACHE_NAME);
        ccfg.setAtomicityMode(ATOMIC);

        return ccfg;
    }

    /**
     * @throws Exception If failed.
     */
    public void testComputeRunScript() throws Exception {
        runJsScript("testComputeRunScript");
    }

    /**
     * @throws Exception If failed.
     */
    public void testComputeExecute() throws Exception {
        runJsScript("testComputeExecute");
    }

    /**
     * @throws Exception If failed.
     */
    public void testComputeFuncWithErrorExecute() throws Exception {
        runJsScript("testComputeFuncWithErrorExecute");
    }

    /**
     * @throws Exception If failed.
     */
    public void testComputeIncorrectFuncExecute() throws Exception {
        runJsScript("testComputeIncorrectFuncExecute");
    }

    /**
     * @throws Exception If failed.
     */
    public void testComputeIncorrectMapExecute() throws Exception {
        runJsScript("testComputeIncorrectMapExecute");
    }

    /**
     * @throws Exception If failed.
     */
    public void testComputeCacheSizeExecute() throws Exception {
        runJsScript("testComputeCacheSizeExecute");
    }

    /**
     * @throws Exception If failed.
     */
    public void testComputeCacheExecute() throws Exception {
        runJsScript("testComputeCacheExecute");
    }

    /**
     * @throws Exception If failed.
     */
    public void testComputeRunScriptContainsKey() throws Exception {
        runJsScript("testComputeRunScriptContainsKey");
    }

    /**
     * @throws Exception If failed.
     */
    public void testComputeRunScriptContainsKeys() throws Exception {
        runJsScript("testComputeRunScriptContainsKeys");
    }

    /**
     * @throws Exception If failed.
     */
    public void testComputeRunScriptPutAllGetAll() throws Exception {
        runJsScript("testComputeRunScriptPutAllGetAll");
    }

    /**
     * @throws Exception If failed.
     */
    public void testComputeRunScriptRemoveOperations() throws Exception {
        runJsScript("testComputeRunScriptRemoveOperations");
    }

    /**
     * @throws Exception If failed.
     */
    public void testComputeMapReduceGetAndPut() throws Exception {
        runJsScript("testComputeMapReduceGetAndPut");
    }

    /**
     * @throws Exception If failed.
     */
    public void testComputeMapReduceGetAndRemoveObject() throws Exception {
        runJsScript("testComputeMapReduceGetAndRemoveObject");
    }

    /**
     * @throws Exception If failed.
     */
    public void testComputeAffinityRunScriptContainsKey() throws Exception {
        runJsScript("testComputeAffinityRunScriptContainsKey");
    }

    /**
     * @throws Exception If failed.
     */
    public void testRestartGrid() throws Exception {
        final AtomicInteger id = new AtomicInteger(2);
        IgniteInternalFuture<Long> fut = GridTestUtils.runMultiThreadedAsync(new Callable<Object>() {
            @Override public Object call() throws Exception {
                ArrayList<Integer> ids = new ArrayList<>();

                for (int i = 0 ; i < 3; ++i) {
                    int cur = id.getAndIncrement();

                    startGrid(cur);

                    ids.add(cur);
                }

                for (Integer id1 : ids)
                    stopGrid(id1);

                return null;
            }
        }, 2, "runIgnite");

        while (!fut.isDone())
            runJsScript("testComputeAllNodeExecute");

        stopGrid(1);

        U.sleep(500);

        startGrid(1);

        U.sleep(3000);
    }
}
