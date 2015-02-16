package io.tracee.examples.webapp;

import io.tracee.Tracee;
import io.tracee.TraceeConstants;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TraceeIds {

	public String getRequestId() {
		return Tracee.getBackend().get(TraceeConstants.INVOCATION_ID_KEY);
	}

	public String getSessionId() {
		return Tracee.getBackend().get(TraceeConstants.SESSION_ID_KEY);
	}

}
