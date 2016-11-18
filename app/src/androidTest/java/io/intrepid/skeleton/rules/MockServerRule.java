package io.intrepid.skeleton.rules;

import android.support.annotation.RawRes;
import android.support.test.InstrumentationRegistry;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.InputStream;

import io.intrepid.skeleton.testutils.TestFileUtils;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * A test rule that sets up and starts a mock server before the test, and then shut it down after the
 * test is finished
 */
public class MockServerRule implements TestRule {
    private final MockWebServer server = new MockWebServer();
    private String url;

    /**
     * Enqueues a file as the response of the next server request
     *
     * @param jsonRes The raw resource id. The file should be under the "androidTest/res/raw" directory
     */
    public void enqueueResponse(@RawRes int jsonRes) {
        InputStream inputStream = InstrumentationRegistry.getContext().getResources().openRawResource(jsonRes);
        String serverResponse = TestFileUtils.convertStreamToString(inputStream);
        server.enqueue(new MockResponse().setResponseCode(200).setBody(serverResponse));
    }

    /**
     * Returns the url of the mock server. This is usually passed into the Retrofit client so that
     * Retrofit will use the mock server
     */
    public String getServerUrl() {
        return url;
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    server.start();
                    url = server.url("/").toString();
                    base.evaluate();
                } finally {
                    server.shutdown();
                }
            }
        };
    }
}
