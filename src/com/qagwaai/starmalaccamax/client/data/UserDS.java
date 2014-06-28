/**
 * UserDS.java
 * Created by pgirard at 2:49:07 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.admin.data package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.event.UserAddedEvent;
import com.qagwaai.starmalaccamax.client.event.UserRemovedEvent;
import com.qagwaai.starmalaccamax.client.event.UserUpdatedEvent;
import com.qagwaai.starmalaccamax.client.event.UsersLoadedEvent;
import com.qagwaai.starmalaccamax.client.service.UserServiceAsync;
import com.qagwaai.starmalaccamax.client.service.action.AddUser;
import com.qagwaai.starmalaccamax.client.service.action.AddUserResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetAllUsers;
import com.qagwaai.starmalaccamax.client.service.action.GetAllUsersResponse;
import com.qagwaai.starmalaccamax.client.service.action.RemoveUser;
import com.qagwaai.starmalaccamax.client.service.action.RemoveUserResponse;
import com.qagwaai.starmalaccamax.client.service.action.UpdateUser;
import com.qagwaai.starmalaccamax.client.service.action.UpdateUserResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.AddedUser;
import com.qagwaai.starmalaccamax.client.service.helpers.GotAllUsers;
import com.qagwaai.starmalaccamax.client.service.helpers.RemovedUser;
import com.qagwaai.starmalaccamax.client.service.helpers.UpdatedUser;
import com.qagwaai.starmalaccamax.shared.model.UserDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author pgirard
 * 
 */
public final class UserDS extends GwtRpcDataSource {

    /**
	 * 
	 */
    public static final String DS_TYPE = "User";

    /**
     * 
     * @param from
     *            to copy from
     * @param to
     *            to copy to
     * @throws DataException
     *             if the operation failed
     */
    private static void copyValues(final ListGridRecord from, final UserRecord to) throws DataException {
        to.setId(LongConverter.fromJavaScript(from, UserRecord.ID));
        to.setEmail(from.getAttribute(UserRecord.EMAIL));
        to.setRealName(from.getAttribute(UserRecord.REALNAME));
        to.setNickname(from.getAttribute(UserRecord.NICKNAME));
        to.setPlayerName(from.getAttribute(UserRecord.PLAYERNAME));
        to.setAppengineUniqueId(from.getAttribute(UserRecord.APPENGINEUNIQUEID));
        to.setNoob(from.getAttributeAsBoolean(UserRecord.NOOB));
        to.setAdmin(from.getAttributeAsBoolean(UserRecord.ADMIN));
        to.setRating(from.getAttributeAsInt(UserRecord.RATING));
        to.setActive(from.getAttributeAsBoolean(UserRecord.ACTIVE));
        to.setTimezone(from.getAttributeAsString(UserRecord.TIMEZONE));
        to.setLastLoggedin(from.getAttributeAsString(UserRecord.LAST_LOGGED_IN));
        to.setNPC(from.getAttributeAsBoolean(UserRecord.ISNPC));
        to.setProfileImageBlobKey(from.getAttributeAsString(UserRecord.PROFILE_IMAGE_BLOB_KEY));
        to.setHasCaptains(from.getAttributeAsBoolean(UserRecord.HAS_CAPTAINS));
    }

    /**
     * 
     */
    private UserServiceAsync dataService = null;

    /**
     * 
     */
    private EventBus eventBus = null;

    /**
     * constructor and pull initial data set
     * 
     * @param id
     *            id of the data source
     * @param dataService
     *            the service to use to interact with the data store - this is a
     *            GWT RPC data source
     * @param eventBus
     *            the bus to publish events on
     */
    public UserDS(final String id, final UserServiceAsync dataService, final EventBus eventBus) {
        super();
        setID(id);

        this.dataService = dataService;
        this.eventBus = eventBus;

        DataSourceIntegerField pkField = new DataSourceIntegerField(UserRecord.ID);
        // pkField.setHidden(true);
        pkField.setPrimaryKey(true);
        pkField.setCanEdit(false);

        DataSourceTextField emailField = new DataSourceTextField(UserRecord.EMAIL, "Email");

        DataSourceTextField realNameField = new DataSourceTextField(UserRecord.REALNAME, "Real Name");

        DataSourceTextField nickNameField = new DataSourceTextField(UserRecord.NICKNAME, "Nick Name");

        DataSourceTextField playerNameField = new DataSourceTextField(UserRecord.PLAYERNAME, "Player Name");

        DataSourceTextField appengineUniqueIdField =
            new DataSourceTextField(UserRecord.APPENGINEUNIQUEID, "App Engine Id");
        appengineUniqueIdField.setCanEdit(false);

        DataSourceIntegerField ratingField = new DataSourceIntegerField(UserRecord.RATING, "Rating");
        SpinnerItem spinnerItem = new SpinnerItem();
        spinnerItem.setDefaultValue(5);
        spinnerItem.setMin(0);
        spinnerItem.setMax(10);
        ratingField.setEditorType(spinnerItem);

        DataSourceBooleanField noobField = new DataSourceBooleanField(UserRecord.NOOB, "Is Noob");

        DataSourceBooleanField adminField = new DataSourceBooleanField(UserRecord.ADMIN, "Is Admin");

        DataSourceBooleanField activeField = new DataSourceBooleanField(UserRecord.ACTIVE, "Is Active");

        DataSourceTextField timezoneField = new DataSourceTextField(UserRecord.TIMEZONE, "Timezone");

        DataSourceTextField lastLoggedInField = new DataSourceTextField(UserRecord.LAST_LOGGED_IN, "Last Logged In");
        lastLoggedInField.setCanEdit(false);
        DataSourceBooleanField npcField = new DataSourceBooleanField(UserRecord.ISNPC, "Is NPC");

        DataSourceTextField profileImageKeyField =
            new DataSourceTextField(UserRecord.PROFILE_IMAGE_BLOB_KEY, "Profile Image Key");
        profileImageKeyField.setCanEdit(false);

        DataSourceBooleanField hasCaptains = new DataSourceBooleanField(UserRecord.HAS_CAPTAINS, "Has Captains");
        hasCaptains.setCanEdit(false);

        setFields(pkField, emailField, realNameField, nickNameField, playerNameField, appengineUniqueIdField,
            ratingField, noobField, adminField, activeField, timezoneField, lastLoggedInField, npcField,
            profileImageKeyField, hasCaptains);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void executeAdd(final String requestId, final DSRequest request, final DSResponse response)
        throws DataException {

        GWT.log("UserDS.transformRequest.ADD");
        JavaScriptObject data = request.getData();
        ListGridRecord rec = new ListGridRecord(data);
        UserRecord userRecord = new UserRecord();
        copyValues(rec, userRecord);

        dataService.execute(new AddUser(userRecord.toUser()), new AddedUser() {
            @Override
            public void got(final UserDTO user) {
                UserAddedEvent.fire(eventBus, user);

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onFailure(final Throwable caught) {
                Window.alert("Add User failed");
                response.setStatus(-1);
                processResponse(requestId, response);
                super.onFailure(caught);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onSuccess(final AddUserResponse result) {
                DSResponse response = new DSResponse();

                if (result.getUser() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    UserRecord[] records = new UserRecord[1];
                    records[0] = new UserRecord(result.getUser());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    UserRecord[] records = new UserRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
                super.onSuccess(result);
            }
        });
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void executeFetch(final String requestId, final DSRequest request, final DSResponse response)
        throws DataException {
        GWT.log("UserDS.transformRequest.FETCH");
        // Criteria criteria = request.getCriteria();

        dataService.execute(new GetAllUsers(request.getStartRow(), request.getEndRow()), new GotAllUsers() {

            public void got(final ArrayList<UserDTO> users) {
                UsersLoadedEvent.fire(eventBus, users);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onFailure(final Throwable caught) {
                Window.alert("Get User failed");
                response.setStatus(-1);
                processResponse(requestId, response);
                super.onFailure(caught);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onSuccess(final GetAllUsersResponse result) {
                DSResponse response = new DSResponse();
                ArrayList<UserDTO> users = result.getUsers();
                if (result.getUsers().size() > 0) {
                    response.setTotalRows(users.size());
                    response.setStartRow(0);
                    response.setEndRow(users.size() - 1);
                    UserRecord[] records = new UserRecord[users.size() + 1];
                    for (int i = 0; i < users.size(); i++) {
                        records[i] = new UserRecord(users.get(i));
                    }
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    UserRecord[] records = new UserRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
                super.onSuccess(result);
            }
        });

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void executeRemove(final String requestId, final DSRequest request, final DSResponse response)
        throws DataException {
        GWT.log("UserDS.transformRequest.REMOVE");
        // Retrieve record which should be removed.
        JavaScriptObject data = request.getData();
        final ListGridRecord rec = new ListGridRecord(data);
        UserRecord testRec = new UserRecord();
        copyValues(rec, testRec);

        dataService.execute(new RemoveUser(testRec.toUser()), new RemovedUser() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void onFailure(final Throwable caught) {
                Window.alert("Get User failed");
                response.setStatus(-1);
                processResponse(requestId, response);
                super.onFailure(caught);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onSuccess(final RemoveUserResponse result) {
                ListGridRecord[] list = new ListGridRecord[1];
                // We do not receive removed record from server.
                // Return record from request.
                list[0] = rec;
                response.setData(list);
                processResponse(requestId, response);
                super.onSuccess(result);
            }

            @Override
            public void removed(final UserDTO user) {
                UserRemovedEvent.fire(eventBus, user);
            }
        });
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void executeUpdate(final String requestId, final DSRequest request, final DSResponse response)
        throws DataException {
        GWT.log("UserDS.transformRequest.UPDATE");
        // Retrieve record which should be updated.
        JavaScriptObject data = request.getData();
        ListGridRecord rec = new ListGridRecord(data);
        // Find grid
        Canvas obj = Canvas.getById(request.getComponentId());
        if (obj instanceof DynamicForm) {
            // DynamicForm form = (DynamicForm) obj;
            // rec = (ListGridRecord) form.
        } else {
            ListGrid grid = (ListGrid) obj;
            // Get record with old and new values combined
            int index = grid.getRecordIndex(rec);
            rec = (ListGridRecord) grid.getEditedRecord(index);
        }
        UserRecord userRecord = new UserRecord();
        copyValues(rec, userRecord);

        dataService.execute(new UpdateUser(userRecord.toUser()), new UpdatedUser() {
            @Override
            public void got(final UserDTO user) {
                UserUpdatedEvent.fire(eventBus, user);

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onFailure(final Throwable caught) {
                Window.alert("Update User failed");
                response.setStatus(-1);
                processResponse(requestId, response);
                super.onFailure(caught);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onSuccess(final UpdateUserResponse result) {
                DSResponse response = new DSResponse();

                if (result.getUser() != null) {
                    response.setTotalRows(1);
                    response.setStartRow(0);
                    response.setEndRow(1);
                    UserRecord[] records = new UserRecord[1];
                    records[0] = new UserRecord(result.getUser());
                    response.setData(records);
                } else {
                    response.setTotalRows(0);
                    response.setStartRow(0);
                    response.setEndRow(0);
                    UserRecord[] records = new UserRecord[0];
                    response.setData(records);
                }
                processResponse(requestId, response);
                super.onSuccess(result);
            }
        });

    }

    /**
     * @return the dataService
     */
    public UserServiceAsync getDataService() {
        return dataService;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected EventBus getEventBus() {
        return this.eventBus;
    }

    /**
     * @param dataService
     *            the dataService to set
     */
    public void setDataService(final UserServiceAsync dataService) {
        this.dataService = dataService;
    }

}
