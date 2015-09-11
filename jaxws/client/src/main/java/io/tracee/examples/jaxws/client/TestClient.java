package io.tracee.examples.jaxws.client;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import io.tracee.Tracee;
import io.tracee.TraceeBackend;
import io.tracee.TraceeConstants;
import io.tracee.examples.jaxws.client.testclient.TraceeJaxWsTestService;
import io.tracee.examples.jaxws.client.testclient.TraceeJaxWsTestWS;

public final class TestClient {

	public static final Logger LOG = LoggerFactory.getLogger(TestClient.class);

    private TestClient() {

    }

    public static void main(final String[] args) throws MalformedURLException {

        final TraceeBackend traceeBackend = Tracee.getBackend();

        final TraceeJaxWsTestService testWebservice = new TraceeJaxWsTestService(new URL(
                "http://localhost:8080/traceeJaxwsTestService/webservices/TraceeJaxWsTestService?wsdl"));
        //testWebservice.setHandlerResolver(new TraceeClientHandlerResolver().getHandlerChain().add(TraceeClientHandler.class).build());

        final TraceeJaxWsTestWS ws = testWebservice.getPort(TraceeJaxWsTestWS.class);

        final int a = 2;
        final int b = 3;

        traceeBackend.remove(TraceeConstants.INVOCATION_ID_KEY);
        MDC.remove(TraceeConstants.INVOCATION_ID_KEY);

        LOG.info("WS CALL WITH NONEXISTING REQUEST_ID : " + a + "+" + b + "=" + ws.sum(a, b));

        traceeBackend.put(TraceeConstants.INVOCATION_ID_KEY, "XYX");
		LOG.info("WS CALL WITH EXISTING REQUEST_ID : " + a + "+" + b + "=" + ws.sum(a, b));
        traceeBackend.remove(TraceeConstants.INVOCATION_ID_KEY);
        MDC.remove(TraceeConstants.INVOCATION_ID_KEY);

        /*
         * try {
         * ws.error(a,b);
         * } catch (Exception e) {
         * traceeLogger.error("GOT EXPECTED EXCEPTION");
         * }
         */

    }

}
