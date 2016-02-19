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

package org.apache.ignite.testsuites;

import junit.framework.TestSuite;
import org.apache.ignite.internal.processors.json.IgniteJsonArrayBuilderSelfTest;
import org.apache.ignite.internal.processors.json.IgniteJsonArraySelfTest;
import org.apache.ignite.internal.processors.json.IgniteJsonCacheTest;
import org.apache.ignite.internal.processors.json.IgniteJsonObjectBuilderSelfTest;
import org.apache.ignite.internal.processors.json.IgniteJsonObjectSelfTest;

/**
 * JSON support test suite.
 */
public class IgniteJsonTestSuite extends TestSuite {
    /**
     * @return Suite.
     * @throws Exception If failed.
     */
    public static TestSuite suite() throws Exception {
        TestSuite suite = new TestSuite("Ignite JSON Test Suite");

        suite.addTest(new TestSuite(IgniteJsonArrayBuilderSelfTest.class));
        suite.addTest(new TestSuite(IgniteJsonObjectBuilderSelfTest.class));

        suite.addTest(new TestSuite(IgniteJsonObjectSelfTest.class));
        suite.addTest(new TestSuite(IgniteJsonArraySelfTest.class));

        suite.addTest(new TestSuite(IgniteJsonCacheTest.class));

        return suite;
    }
}
