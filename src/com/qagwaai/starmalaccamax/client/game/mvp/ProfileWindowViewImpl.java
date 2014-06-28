/**
 * ProfileWindowView.java
 * Created by pgirard at 11:09:58 AM on Jul 26, 2010
 * in the com.qagwaai.starmalaccamax.client.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateTimeItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.ResetItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * @author pgirard
 * 
 */
public class ProfileWindowViewImpl extends AbstractView<ProfileWindowPresenterImpl> implements ProfileWindowView {

    /**
	 * 
	 */
    private Window rootWindow;

    /**
	 * 
	 */
    private TextItem usernameItem = new TextItem("Username");

    /**
	 * 
	 */
    private TextItem emailItem = new TextItem("Email");

    /**
	 * 
	 */
    private DateTimeItem lastLoggedIn = new DateTimeItem("LastLoggedIn", "Last Logged In");

    /**
	 * 
	 */
    private SubmitItem saveItem = new SubmitItem("Save");

    /**
	 * 
	 */
    private ResetItem resetItem = new ResetItem("Reset");
    /**
	 * 
	 */
    private CheckboxItem isNewItem = new CheckboxItem();

    /**
	 * 
	 */
    private SelectItem regionItem = new SelectItem("Region-Locale");

    /**
	 * 
	 */
    private ComboBoxItem frequencyItem = new ComboBoxItem("emailFrequency", "Email Frequency");

    /**
	 * 
	 */
    private CheckboxItem isAdminItem = new CheckboxItem();

    /**
	 * 
	 */
    private SelectItem timezoneItem = new SelectItem("Timezone");

    /**
	 * 
	 */
    private CheckboxItem isNPCItem = new CheckboxItem();

    /**
	 * 
	 */
    private CheckboxItem isUserActive = new CheckboxItem();

    /**
	 * 
	 */
    private IntegerItem ratingItem = new IntegerItem("Rating");

    /**
	 * 
	 */
    private FileUpload fileItem = new FileUpload();

    /**
	 * 
	 */
    private Img profileImage = new Img();

    /**
	 * 
	 */
    private FormPanel uploadForm = new FormPanel();

    /**
	 * 
	 */
    private Button uploadButton = new Button("Upload");

    /**
	 * 
	 */
    private HorizontalPanel uploadPanel = new HorizontalPanel();

    /**
	 * 
	 */
    public ProfileWindowViewImpl() {
        rootWindow = new Window();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Widget asWidget() {
        return rootWindow;
    }

    /**
	 * 
	 */
    public final void closeWindow() {
        rootWindow.destroy();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void destroy() {
        closeWindow();

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getEmailItem()
	 */
    @Override
	public final TextItem getEmailItem() {
        return emailItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getIsNewItem()
	 */
    @Override
	public final CheckboxItem getIsNewItem() {
        return isNewItem;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final Canvas getRootCanvas() {
        return rootWindow;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getUsernameItem()
	 */
    @Override
	public final TextItem getUsernameItem() {
        return usernameItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void layout() {

        rootWindow.setWidth(400);
        rootWindow.setHeight(500);
        rootWindow.setTitle("Profile");
        rootWindow.setIsModal(true);
        rootWindow.setShowModalMask(true);
        rootWindow.setCanDragResize(true);
        rootWindow.setCanDragReposition(true);
        rootWindow.setShowMinimizeButton(false);
        if (getPresenter().isCurrentUserAdmin()) {
            rootWindow.setWidth(530);
        }

        final TabSet topTabSet = new TabSet();
        topTabSet.setTabBarPosition(Side.TOP);
        topTabSet.setTabBarAlign(Side.RIGHT);
        topTabSet.setWidth100();
        topTabSet.setHeight100();

        Tab profileTab = new Tab("Profile", "icons/16/person.png");
        profileTab.setPane(createProfileTab());

        Tab preferencesTab = new Tab("Preferences", "icons/16/message.png");
        preferencesTab.setPane(createPreferencesTab());

        Tab badgesTab = new Tab("Badges", "icons/16/award_star_gold_2.png");

        topTabSet.addTab(profileTab);
        topTabSet.addTab(preferencesTab);
        topTabSet.addTab(badgesTab);

        rootWindow.addItem(topTabSet);

    }

    /**
     * 
     * @return the created preferences tab
     */
    private VLayout createPreferencesTab() {
        VLayout preferencesRootLayout = new VLayout();
        final DynamicForm userForm = new DynamicForm();
        userForm.setWidth(250);

        HTMLPane communicationBlurb = new HTMLPane();
        communicationBlurb.setWidth(250);
        communicationBlurb
            .setContents("Communication preferences allow you to determine how often a message will be sent to your inbox about events within the game.");
        communicationBlurb.setHeight(75);
        frequencyItem.setValueMap("Immediately", "Daily Summary");

        userForm.setFields(frequencyItem);

        preferencesRootLayout.addMember(communicationBlurb);
        preferencesRootLayout.addMember(userForm);

        return preferencesRootLayout;
    }

    /**
     * 
     * @return the created profile tab
     */
    private HLayout createProfileTab() {
        HLayout profileRootLayout = new HLayout();
        final DynamicForm userForm = new DynamicForm();
        userForm.setWidth(250);

        usernameItem.setRequired(true);

        emailItem.setRequired(true);

        isNewItem.setTitle("Is new User");

        regionItem.setType("comboBox");
        regionItem.setImageURLPrefix("flags/16/");
        regionItem.setImageURLSuffix(".png");

        ratingItem.setDisabled(true);

        userForm.setFields(usernameItem, emailItem, regionItem, timezoneItem, ratingItem, saveItem);

        final DynamicForm adminForm = new DynamicForm();
        adminForm.setWidth(200);

        isNewItem.setDisabled(true);
        lastLoggedIn.setDisabled(true);
        lastLoggedIn.setWidth(200);
        isAdminItem.setDisabled(true);
        isNPCItem.setDisabled(true);
        isAdminItem.setTitle("Is Admin");
        isNPCItem.setTitle("Is NPC");
        isUserActive.setTitle("Is Active");

        adminForm.setFields(isNewItem, lastLoggedIn, isAdminItem, isNPCItem, isUserActive);

        uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
        uploadForm.setMethod(FormPanel.METHOD_POST);
        uploadForm.setAction("/profileImageUpload");

        fileItem.setWidth("170px");
        fileItem.setName("upfile");
        uploadButton.setEnabled(false);

        uploadForm.add(fileItem);
        uploadPanel.setHeight("25px");
        uploadPanel.add(uploadForm);
        uploadPanel.add(uploadButton);

        profileImage.setWidth(128);
        profileImage.setHeight(128);
        profileImage.setShowEdges(true);

        HTML line = new HTML("<hr/>");
        line.setHeight("10px");

        VLayout memberLayout = new VLayout();
        memberLayout.addMember(userForm);
        memberLayout.addMember(line);
        memberLayout.addMember(profileImage);
        memberLayout.addMember(uploadPanel);

        profileRootLayout.addMember(memberLayout);
        profileRootLayout.addMember(adminForm);

        return profileRootLayout;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public final void render() {
        rootWindow.setAutoCenter(true);
        rootWindow.show();

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getSaveItem()
	 */
    @Override
	public final SubmitItem getSaveItem() {
        return saveItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getRegionItem()
	 */
    @Override
	public final SelectItem getRegionItem() {
        return regionItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getLastLoggedIn()
	 */
    @Override
	public final DateTimeItem getLastLoggedIn() {
        return lastLoggedIn;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getResetItem()
	 */
    @Override
	public final ResetItem getResetItem() {
        return resetItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getIsAdminItem()
	 */
    @Override
	public final CheckboxItem getIsAdminItem() {
        return isAdminItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getTimezoneItem()
	 */
    @Override
	public final SelectItem getTimezoneItem() {
        return timezoneItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getIsNPCItem()
	 */
    @Override
	public final CheckboxItem getIsNPCItem() {
        return isNPCItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getRatingItem()
	 */
    @Override
	public final IntegerItem getRatingItem() {
        return ratingItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getUploadForm()
	 */
    @Override
	public final FormPanel getUploadForm() {
        return uploadForm;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getUploadButton()
	 */
    @Override
	public final Button getUploadButton() {
        return uploadButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getUploadPanel()
	 */
    @Override
	public final HorizontalPanel getUploadPanel() {
        return uploadPanel;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getProfileImage()
	 */
    @Override
	public final Img getProfileImage() {
        return profileImage;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.ProfileWindowView#getIsUserActive()
	 */
    @Override
	public final CheckboxItem getIsUserActive() {
        return isUserActive;
    }

}
