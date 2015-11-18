
package org.broken.command;

import org.broken.required.RequiredService;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.apache.karaf.shell.api.action.lifecycle.Reference;

@Command(scope = "interceptor", name = "test", description = "calling methods to test tx cascade with interceptors enabled")
@Service
public class InterceptorTestCommand implements Action {

    @Reference
    RequiredService requiredService;

    @Override
    public Object execute() throws Exception {
        if (requiredService != null) {
            requiredService.callMandatoryFromRequired();
            requiredService.callMandatoryFromRequiresNew();
            requiredService.callMandatoryFromNotSupported();
            requiredService.callMandatoryFromSupports();
            requiredService.callMandatoryFromNever();
        }
        return null;
    }
}
