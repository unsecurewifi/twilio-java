/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.call;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class RecordingUpdater extends Updater<Recording> {
    private String pathAccountSid;
    private final String pathCallSid;
    private final String pathSid;
    private final Recording.Status status;

    /**
     * Construct a new RecordingUpdater.
     * 
     * @param pathCallSid The call_sid
     * @param pathSid The recording sid (or use 'Twilio.CURRENT' instead of
     *                recording sid to reference current active recording for
     *                update.)
     * @param status The status to change the recording to.
     */
    public RecordingUpdater(final String pathCallSid, 
                            final String pathSid, 
                            final Recording.Status status) {
        this.pathCallSid = pathCallSid;
        this.pathSid = pathSid;
        this.status = status;
    }

    /**
     * Construct a new RecordingUpdater.
     * 
     * @param pathAccountSid The account_sid
     * @param pathCallSid The call_sid
     * @param pathSid The recording sid (or use 'Twilio.CURRENT' instead of
     *                recording sid to reference current active recording for
     *                update.)
     * @param status The status to change the recording to.
     */
    public RecordingUpdater(final String pathAccountSid, 
                            final String pathCallSid, 
                            final String pathSid, 
                            final Recording.Status status) {
        this.pathAccountSid = pathAccountSid;
        this.pathCallSid = pathCallSid;
        this.pathSid = pathSid;
        this.status = status;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated Recording
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Recording update(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Calls/" + this.pathCallSid + "/Recordings/" + this.pathSid + ".json",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Recording update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return Recording.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (status != null) {
            request.addPostParam("Status", status.toString());
        }
    }
}