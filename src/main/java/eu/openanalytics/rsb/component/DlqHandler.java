/*
 *   R Service Bus
 *   
 *   Copyright (c) Copyright of OpenAnalytics BVBA, 2010-2011
 *
 *   ===========================================================================
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Affero General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Affero General Public License for more details.
 *
 *   You should have received a copy of the GNU Affero General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.openanalytics.rsb.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import eu.openanalytics.rsb.message.AbstractJob;
import eu.openanalytics.rsb.message.AbstractResult;
import eu.openanalytics.rsb.message.AbstractWorkItem;

/**
 * Handles messages that end up in the dead letter queue.
 * 
 * @author "Open Analytics <rsb.development@openanalytics.eu>"
 */
@Component("dlqHandler")
public class DlqHandler {
    private final static Log LOGGER = LogFactory.getLog(DlqHandler.class);

    /**
     * Handles a job whose processing has failed repetitively.
     * 
     * @param job
     */
    public void handle(final AbstractJob job) {
        logAndAlertFailure(job);
        // FIXME build a response and send to response queue
    }

    /**
     * Handles a result whose delivery has failed repetitively.
     * 
     * @param result
     */
    public void handle(final AbstractResult result) {
        logAndAlertFailure(result);
    }

    private void logAndAlertFailure(final AbstractWorkItem workItem) {
        LOGGER.error("Abandonning processing of: " + workItem);
        // FIXME email rsb admin
    }
}