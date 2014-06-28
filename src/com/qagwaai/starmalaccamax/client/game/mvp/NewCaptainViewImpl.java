/**
 * NewCaptainView.java
 * Created by pgirard at 2:27:12 PM on Aug 10, 2010
 * in the com.qagwaai.starmalaccamax.client.view package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractView;
import com.qagwaai.starmalaccamax.client.service.action.GetLocationForNewCaptainResponse;
import com.qagwaai.starmalaccamax.shared.model.LocationDTO;
import com.qagwaai.starmalaccamax.shared.model.Planet;
import com.qagwaai.starmalaccamax.shared.model.SolarSystem;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Slider;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ColorPickerItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author pgirard
 * 
 */
public final class NewCaptainViewImpl extends AbstractView<NewCaptainPresenter> implements NewCaptainView {

    private static final int DESCRIPTION_HEIGHT = 395;
    private static final int FORM_MARGIN = 10;
    private static final int VIEW_HEIGHT = 400;
    private static final int VIEW_WIDTH = 800;
    private static final int MARGIN = 10;
    private static final int SHADOW_SOFTNESS = 10;
    private LocationDTO shipLocation;
    /**
	 * 
	 */
    private Window rootWindow;
    /**
	 * 
	 */
    private VLayout rootContainer = new VLayout();
    /**
	 * 
	 */
    private HLayout[] pages = new HLayout[6];
    /**
	 * 
	 */
    private Button nextButton = new Button("Next");
    /**
	 * 
	 */
    private Button previousButton = new Button("Previous");
    /**
	 * 
	 */
    private int currentPage;
    private DynamicForm nameForm = new DynamicForm();

    /**
	 * 
	 */
    private ColorPickerItem colorPicker = new ColorPickerItem("Color");
    /**
	 * 
	 */
    private TextItem nameTextItem = new TextItem("captainName", "Name");

    private TextItem shipNameItem = new TextItem("shipName", "Name");
    /**
	 * 
	 */
    private Label totalSkillAvailable = new Label("Total Points Available: " + NewCaptainPresenterImpl.TOTAL_SKILL_POINTS);
    /**
	 * 
	 */
    private Label totalAttributeAvailable = new Label("Total Points Available: "
        + NewCaptainPresenterImpl.TOTAL_ATTRIBUTE_POINTS);
    /**
	 * 
	 */
    private Slider negotiationItem = new Slider("Negotiation");
    /**
	 * 
	 */
    private Slider navigationItem = new Slider("Navigation");
    /**
	 * 
	 */
    private Slider enginesItem = new Slider("Engines");
    /**
	 * 
	 */
    private Slider missilesItem = new Slider("Missiles");
    /**
	 * 
	 */
    private Slider lasersItem = new Slider("Lasers");
    /**
	 * 
	 */
    private Slider repairItem = new Slider("Repair");
    /**
	 * 
	 */
    private Slider defenseItem = new Slider("Defense");
    /**
	 * 
	 */
    private Slider shieldsItem = new Slider("Shields");
    /**
	 * 
	 */
    private Slider storageItem = new Slider("Storage");
    /**
	 * 
	 */
    private Slider intelligenceItem = new Slider("Intelligence");
    /**
	 * 
	 */
    private Slider knowledgeItem = new Slider("Knowledge");

    /**
	 * 
	 */
    private TextItem summaryNameItem = new TextItem("Name");
    /**
	 * 
	 */
    private ColorPickerItem summaryColorPicker = new ColorPickerItem("Color");
    /**
	 * 
	 */
    private TextItem summaryNegotiationItem = new TextItem("Negotiation");
    /**
	 * 
	 */
    private TextItem summaryStorageItem = new TextItem("Storage");
    /**
	 * 
	 */
    private TextItem summaryNavigationItem = new TextItem("Navigation");
    /**
	 * 
	 */
    private TextItem summaryShieldsItem = new TextItem("Shields");
    /**
	 * 
	 */
    private TextItem summaryLasersItem = new TextItem("Lasers");
    /**
	 * 
	 */
    private TextItem summaryMissilesItem = new TextItem("Missiles");
    /**
	 * 
	 */
    private TextItem summaryEnginesItem = new TextItem("Engines");
    /**
	 * 
	 */
    private TextItem summaryRepairItem = new TextItem("Repair");
    /**
	 * 
	 */
    private TextItem summaryDefenseItem = new TextItem("Defense");
    /**
	 * 
	 */
    private TextItem summaryIntelligenceItem = new TextItem("Intelligence");
    /**
	 * 
	 */
    private TextItem summaryKnowledgeItem = new TextItem("Knowledge");

    private Img captainImage = new Img();

    private IButton captainImageButtonNext = new IButton("Next");

    private IButton captainImageButtonPrevious = new IButton("Previous");

    private RadioGroupItem genderGroupItem = new RadioGroupItem("Gender");

    private RadioGroupItem raceGroupItem = new RadioGroupItem("Race");

    /**
	 * 
	 */
    private static final int SLIDER_HEIGHT = 23;

    /**
	 * 
	 */
    private static final int SLIDER_WIDTH = 250;

    private static final int DESCRIPTION_WIDTH = 300;
    private HTMLPane htmlLocationPane = new HTMLPane();

    /**
	 * 
	 */
    public NewCaptainViewImpl() {
        rootWindow = new Window();
        currentPage = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget asWidget() {
        return rootWindow;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#closeWindow()
	 */
    @Override
	public void closeWindow() {
        rootWindow.destroy();
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#copyValues()
	 */
    @Override
	public void copyValues() {
        summaryNameItem.setValue((String) getNameTextItem().getValue());
        summaryColorPicker.setValue((String) getColorPicker().getValue());
        summaryNegotiationItem.setValue(getNegotiationItem().getValue());
        summaryStorageItem.setValue(getStorageItem().getValue());
        summaryNavigationItem.setValue(getNavigationItem().getValue());
        summaryShieldsItem.setValue(getShieldsItem().getValue());
        summaryLasersItem.setValue(getLasersItem().getValue());
        summaryMissilesItem.setValue(getMissilesItem().getValue());
        summaryEnginesItem.setValue(getEnginesItem().getValue());
        summaryRepairItem.setValue(getRepairItem().getValue());
        summaryDefenseItem.setValue(getDefenseItem().getValue());
        summaryIntelligenceItem.setValue(getIntelligenceItem().getValue());
        summaryKnowledgeItem.setValue(getKnowledgeItem().getValue());

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        closeWindow();

    }

    /**
	 * 
	 */
    private void hideAllPages() {
        for (HLayout page : pages) {
            if (page != null) {
                page.setVisible(false);
            }
        }
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#displayPage(int)
	 */
    @Override
	public void displayPage(final int page) {
        if (page < pages.length) {
            hideAllPages();
            pages[page].setVisible(true);
            currentPage = page;
        }
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getColorPicker()
	 */
    @Override
	public ColorPickerItem getColorPicker() {
        return colorPicker;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getCurrentPage()
	 */
    @Override
	public int getCurrentPage() {
        return currentPage;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getDefenseItem()
	 */
    @Override
	public Slider getDefenseItem() {
        return defenseItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getEnginesItem()
	 */
    @Override
	public Slider getEnginesItem() {
        return enginesItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getIntelligenceItem()
	 */
    @Override
	public Slider getIntelligenceItem() {
        return intelligenceItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getKnowledgeItem()
	 */
    @Override
	public Slider getKnowledgeItem() {
        return knowledgeItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getLasersItem()
	 */
    @Override
	public Slider getLasersItem() {
        return lasersItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getMissilesItem()
	 */
    @Override
	public Slider getMissilesItem() {
        return missilesItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getNameTextItem()
	 */
    @Override
	public TextItem getNameTextItem() {
        return nameTextItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getNavigationItem()
	 */
    @Override
	public Slider getNavigationItem() {
        return navigationItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getNegotiationItem()
	 */
    @Override
	public Slider getNegotiationItem() {
        return negotiationItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getNextButton()
	 */
    @Override
	public Button getNextButton() {
        return nextButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getPreviousButton()
	 */
    @Override
	public Button getPreviousButton() {
        return previousButton;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getRepairItem()
	 */
    @Override
	public Slider getRepairItem() {
        return repairItem;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Canvas getRootCanvas() {
        return rootWindow;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getShieldsItem()
	 */
    @Override
	public Slider getShieldsItem() {
        return shieldsItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getStorageItem()
	 */
    @Override
	public Slider getStorageItem() {
        return storageItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getTotalPages()
	 */
    @Override
	public int getTotalPages() {
        return pages.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void layout() {
        rootWindow.setWidth(VIEW_WIDTH);
        rootWindow.setHeight(VIEW_HEIGHT);
        rootWindow.setAutoSize(true);
        rootWindow.setCanDragResize(true);
        rootWindow.setCanDragReposition(true);
        rootWindow.setTitle("New Captain");
        rootWindow.setShowShadow(true);
        rootWindow.setShadowSoftness(SHADOW_SOFTNESS);
        rootWindow.setShadowOffset(5);
        rootWindow.setIsModal(true);
        rootWindow.setShowModalMask(true);
        rootWindow.setShowMinimizeButton(false);

        rootContainer.setWidth100();
        rootContainer.setHeight100();

        HLayout buttonBar = new HLayout(MARGIN);
        nextButton.setHeight(20);
        previousButton.setHeight(20);
        previousButton.disable();
        Label spacer = new Label();
        spacer.setWidth(25);
        spacer.setHeight(1);
        buttonBar.addMember(spacer);
        buttonBar.addMember(previousButton);
        buttonBar.addMember(nextButton);
        buttonBar.setHeight(30);

        rootContainer.addMember(layoutPage0());
        rootContainer.addMember(layoutPage1());
        rootContainer.addMember(layoutPage2());
        rootContainer.addMember(layoutPage3());
        rootContainer.addMember(layoutPage4());
        rootContainer.addMember(layoutPage5());
        pages[1].setVisible(false);
        pages[2].setVisible(false);
        pages[3].setVisible(false);
        pages[4].setVisible(false);
        pages[5].setVisible(false);
        rootContainer.addMember(buttonBar);

        rootWindow.addItem(rootContainer);

    }

    /**
     * 
     * @return the first page
     */
    private HLayout layoutPage0() {
        HLayout page = new HLayout();
        page.setWidth100();
        page.setHeight(395);
        page.setLeft(0);
        page.setTop(0);

        HTMLPane description = new HTMLPane();
        description.setWidth(DESCRIPTION_WIDTH);
        description.setHeight(DESCRIPTION_HEIGHT);
        description.setContents("Customize your captain using this wizard, including setting images, and attributes. &nbsp;On this first page, "
        		+ "select a captain name that will be unique across the entire game system.");
        description.setMargin(15);

        VLayout pageRight = new VLayout();

        pageRight.setHeight(395);
        pageRight.setWidth100();
        nameForm.setMargin(FORM_MARGIN);
        nameForm.setNumCols(4);

        nameTextItem.setChangeOnKeypress(true);

        colorPicker.setWidth(85);

        HLayout imageBar = new HLayout();
        imageBar.setAlign(Alignment.CENTER);
        imageBar.setWidth100();
        imageBar.setHeight(128);
        captainImage.setHeight(128);
        captainImage.setWidth(128);
        imageBar.addMember(captainImage);

        HLayout buttonBar = new HLayout(5);
        buttonBar.setWidth100();
        buttonBar.setHeight(25);
        buttonBar.setAlign(Alignment.CENTER);
        captainImageButtonNext.setWidth(75);
        captainImageButtonNext.setHeight(25);
        captainImageButtonPrevious.setWidth(75);
        captainImageButtonPrevious.setHeight(25);
        buttonBar.addMember(captainImageButtonPrevious);
        buttonBar.addMember(captainImageButtonNext);

        genderGroupItem.setValueMap("Male", "Female");
        raceGroupItem.setValueMap("Human", "Roughian", "Starling", "Loogracian");

        nameForm.setFields(nameTextItem, colorPicker, genderGroupItem, raceGroupItem);

        Label topSpacer = new Label();
        topSpacer.setHeight(10);
        topSpacer.setWidth(5);
        pageRight.addMember(topSpacer);
        pageRight.addMember(nameForm);
        pageRight.addMember(imageBar);
        pageRight.addMember(buttonBar);

        page.addMember(description);
        page.addMember(pageRight);

        pages[0] = page;
        return page;
    }

    /**
     * 
     * @return the second page
     */
    private HLayout layoutPage1() {
        HLayout page = new HLayout();
        page.setWidth100();
        page.setHeight(395);
        page.setLeft(0);
        page.setTop(0);

        HTMLPane description = new HTMLPane();
        description.setWidth(DESCRIPTION_WIDTH);
        description.setHeight(395);
        description
            .setContents("Customize your captain using this wizard, including setting images, and attributes. &nbsp;"
                + "On this first page, select a captain name that will be unique across the entire game system.");
        description.setMargin(15);

        VLayout pageRight = new VLayout();
        pageRight.setHeight(395);
        pageRight.setWidth100();

        VLayout sliderForm = new VLayout();

        totalSkillAvailable.setHeight(25);

        HLayout negotiationLayout = new HLayout();
        negotiationLayout.setHeight(40);
        negotiationItem.setWidth(SLIDER_WIDTH);
        negotiationItem.setHeight(SLIDER_HEIGHT);
        negotiationItem.setMinValue(0);
        negotiationItem.setValue(0);
        negotiationItem.setMaxValue(10);
        negotiationItem.setShowRange(false);
        negotiationItem.setVertical(false);
        negotiationItem.setNumValues(11);
        String negotiationItemPrompt = "&lt;insert here&gt;";
        HTMLPane negotiationHint = new HTMLPane();
        negotiationHint.setWidth(200);
        negotiationHint.setMargin(5);
        negotiationHint.setContents(negotiationItemPrompt);
        negotiationItem.setPrompt(negotiationItemPrompt);
        negotiationLayout.addMember(negotiationItem);
        negotiationLayout.addMember(negotiationHint);

        navigationItem.setWidth(SLIDER_WIDTH);
        navigationItem.setHeight(SLIDER_HEIGHT);
        navigationItem.setMinValue(0);
        navigationItem.setValue(0);
        navigationItem.setMaxValue(10);
        navigationItem.setShowRange(false);
        navigationItem.setVertical(false);
        navigationItem.setNumValues(11);
        HLayout navigationLayout = new HLayout();
        navigationLayout.setHeight(40);
        String navigationItemPrompt = "&lt;insert here&gt;";
        HTMLPane navigationHint = new HTMLPane();
        navigationHint.setWidth(200);
        navigationHint.setMargin(5);
        navigationHint.setContents(navigationItemPrompt);
        navigationItem.setPrompt(navigationItemPrompt);
        navigationLayout.addMember(navigationItem);
        navigationLayout.addMember(navigationHint);

        shieldsItem.setWidth(SLIDER_WIDTH);
        shieldsItem.setHeight(SLIDER_HEIGHT);
        shieldsItem.setMinValue(0);
        shieldsItem.setValue(0);
        shieldsItem.setMaxValue(10);
        shieldsItem.setShowRange(false);
        shieldsItem.setVertical(false);
        shieldsItem.setNumValues(11);
        HLayout shieldsLayout = new HLayout();
        shieldsLayout.setHeight(40);
        String shieldsItemPrompt = "&lt;insert here&gt;";
        HTMLPane shieldsHint = new HTMLPane();
        shieldsHint.setWidth(200);
        shieldsHint.setMargin(5);
        shieldsHint.setContents(shieldsItemPrompt);
        shieldsItem.setPrompt(shieldsItemPrompt);
        shieldsLayout.addMember(shieldsItem);
        shieldsLayout.addMember(shieldsHint);

        lasersItem.setWidth(SLIDER_WIDTH);
        lasersItem.setHeight(SLIDER_HEIGHT);
        lasersItem.setMinValue(0);
        lasersItem.setValue(0);
        lasersItem.setMaxValue(10);
        lasersItem.setShowRange(false);
        lasersItem.setVertical(false);
        lasersItem.setNumValues(11);
        HLayout lasersLayout = new HLayout();
        lasersLayout.setHeight(40);
        String lasersItemPrompt = "&lt;insert here&gt;";
        HTMLPane lasersHint = new HTMLPane();
        lasersHint.setWidth(200);
        lasersHint.setMargin(5);
        lasersHint.setContents(lasersItemPrompt);
        lasersItem.setPrompt(lasersItemPrompt);
        lasersLayout.addMember(lasersItem);
        lasersLayout.addMember(lasersHint);

        missilesItem.setWidth(SLIDER_WIDTH);
        missilesItem.setHeight(SLIDER_HEIGHT);
        missilesItem.setMinValue(0);
        missilesItem.setValue(0);
        missilesItem.setMaxValue(10);
        missilesItem.setShowRange(false);
        missilesItem.setVertical(false);
        missilesItem.setNumValues(11);
        HLayout missilesLayout = new HLayout();
        missilesLayout.setHeight(40);
        String missilesItemPrompt = "&lt;insert here&gt;";
        HTMLPane missilesHint = new HTMLPane();
        missilesHint.setWidth(200);
        missilesHint.setMargin(5);
        missilesHint.setContents(missilesItemPrompt);
        missilesItem.setPrompt(missilesItemPrompt);
        missilesLayout.addMember(missilesItem);
        missilesLayout.addMember(missilesHint);

        enginesItem.setWidth(SLIDER_WIDTH);
        enginesItem.setHeight(SLIDER_HEIGHT);
        enginesItem.setMinValue(0);
        enginesItem.setValue(0);
        enginesItem.setMaxValue(10);
        enginesItem.setShowRange(false);
        enginesItem.setVertical(false);
        enginesItem.setNumValues(11);
        HLayout enginesLayout = new HLayout();
        enginesLayout.setHeight(40);
        String enginesItemPrompt = "&lt;insert here&gt;";
        HTMLPane enginesHint = new HTMLPane();
        enginesHint.setWidth(200);
        enginesHint.setMargin(5);
        enginesHint.setContents(enginesItemPrompt);
        enginesItem.setPrompt(enginesItemPrompt);
        enginesLayout.addMember(enginesItem);
        enginesLayout.addMember(enginesHint);

        repairItem.setWidth(SLIDER_WIDTH);
        repairItem.setHeight(SLIDER_HEIGHT);
        repairItem.setMinValue(0);
        repairItem.setValue(0);
        repairItem.setMaxValue(10);
        repairItem.setShowRange(false);
        repairItem.setVertical(false);
        repairItem.setNumValues(11);
        HLayout repairLayout = new HLayout();
        repairLayout.setHeight(40);
        String repairItemPrompt = "&lt;insert here&gt;";
        HTMLPane repairHint = new HTMLPane();
        repairHint.setWidth(200);
        repairHint.setMargin(5);
        repairHint.setContents(repairItemPrompt);
        repairItem.setPrompt(repairItemPrompt);
        repairLayout.addMember(repairItem);
        repairLayout.addMember(repairHint);

        defenseItem.setWidth(SLIDER_WIDTH);
        defenseItem.setHeight(SLIDER_HEIGHT);
        defenseItem.setMinValue(0);
        defenseItem.setValue(0);
        defenseItem.setMaxValue(10);
        defenseItem.setShowRange(false);
        defenseItem.setVertical(false);
        defenseItem.setNumValues(11);
        HLayout defenseLayout = new HLayout();
        defenseLayout.setHeight(40);
        String defenseItemPrompt = "&lt;insert here&gt;";
        HTMLPane defenseHint = new HTMLPane();
        defenseHint.setWidth(200);
        defenseHint.setMargin(5);
        defenseHint.setContents(defenseItemPrompt);
        defenseItem.setPrompt(defenseItemPrompt);
        defenseLayout.addMember(defenseItem);
        defenseLayout.addMember(defenseHint);

        storageItem.setWidth(SLIDER_WIDTH);
        storageItem.setHeight(SLIDER_HEIGHT);
        storageItem.setMinValue(0);
        storageItem.setValue(0);
        storageItem.setMaxValue(10);
        storageItem.setShowRange(false);
        storageItem.setVertical(false);
        storageItem.setNumValues(11);
        HLayout storageLayout = new HLayout();
        storageLayout.setHeight(40);
        String storageItemPrompt = "&lt;insert here&gt;";
        HTMLPane storageHint = new HTMLPane();
        storageHint.setWidth(200);
        storageHint.setMargin(5);
        storageHint.setContents(storageItemPrompt);
        storageItem.setPrompt(storageItemPrompt);
        storageLayout.addMember(storageItem);
        storageLayout.addMember(storageHint);

        sliderForm.addMember(negotiationLayout);
        sliderForm.addMember(navigationLayout);
        sliderForm.addMember(shieldsLayout);
        sliderForm.addMember(lasersLayout);
        sliderForm.addMember(missilesLayout);
        sliderForm.addMember(enginesLayout);
        sliderForm.addMember(repairLayout);
        sliderForm.addMember(defenseLayout);
        sliderForm.addMember(storageLayout);

        Label topSpacer = new Label();
        topSpacer.setHeight(10);
        topSpacer.setWidth(5);

        pageRight.addMember(topSpacer);
        pageRight.addMember(totalSkillAvailable);
        pageRight.addMember(sliderForm);

        page.addMember(description);
        page.addMember(pageRight);

        pages[1] = page;
        return page;
    }

    /**
     * 
     * @return the third page
     */
    private HLayout layoutPage2() {

        HLayout page = new HLayout();
        page.setWidth100();
        page.setHeight(395);
        page.setLeft(0);
        page.setTop(0);

        HTMLPane description = new HTMLPane();
        description.setWidth(DESCRIPTION_WIDTH);
        description.setHeight(395);
        description
            .setContents("Customize your captain using this wizard, including setting images, and attributes. &nbsp;"
                + "On this first page, select a captain name that will be unique across the entire game system.");
        description.setMargin(15);

        VLayout pageRight = new VLayout();
        pageRight.setHeight(395);
        pageRight.setWidth100();

        totalAttributeAvailable.setHeight(25);

        VLayout attributeSliders = new VLayout();

        HLayout intelligenceLayout = new HLayout();
        intelligenceLayout.setHeight(70);
        intelligenceItem.setWidth(SLIDER_WIDTH);
        intelligenceItem.setHeight(SLIDER_HEIGHT);
        intelligenceItem.setMinValue(0);
        intelligenceItem.setValue(0);
        intelligenceItem.setMaxValue(10);
        intelligenceItem.setShowRange(false);
        intelligenceItem.setVertical(false);
        intelligenceItem.setNumValues(11);
        String intelligenceItemPrompt =
            "Base attribute affecting how well trade occurs, how efficient the ship is in its daily operation, how well the crew respects the captain...";
        HTMLPane intelligenceHint = new HTMLPane();
        intelligenceHint.setWidth(200);
        intelligenceHint.setMargin(5);
        intelligenceHint.setContents(intelligenceItemPrompt);
        intelligenceItem.setPrompt(intelligenceItemPrompt);
        intelligenceLayout.addMember(intelligenceItem);
        intelligenceLayout.addMember(intelligenceHint);

        HLayout knowledgeLayout = new HLayout();
        intelligenceLayout.setHeight(70);
        knowledgeItem.setWidth(SLIDER_WIDTH);
        knowledgeItem.setHeight(SLIDER_HEIGHT);
        knowledgeItem.setMinValue(0);
        knowledgeItem.setValue(0);
        knowledgeItem.setMaxValue(10);
        knowledgeItem.setShowRange(false);
        knowledgeItem.setVertical(false);
        knowledgeItem.setNumValues(11);
        String knowledgeItemPrompt = "Base attribute affecting how much the captain knows.  &lt;insert here&gt;";
        HTMLPane knowledgeHint = new HTMLPane();
        knowledgeHint.setWidth(200);
        knowledgeHint.setMargin(5);
        knowledgeHint.setContents(knowledgeItemPrompt);
        knowledgeItem.setPrompt(knowledgeItemPrompt);
        knowledgeLayout.addMember(knowledgeItem);
        knowledgeLayout.addMember(knowledgeHint);

        attributeSliders.addMember(intelligenceLayout);
        attributeSliders.addMember(knowledgeLayout);

        Label topSpacer = new Label();
        topSpacer.setHeight(10);
        topSpacer.setWidth(5);

        pageRight.addMember(topSpacer);
        pageRight.addMember(totalAttributeAvailable);
        pageRight.addMember(attributeSliders);

        page.addMember(description);
        page.addMember(pageRight);

        pages[2] = page;
        return page;
    }

    /**
     * 
     * @return the summary and last page page
     */
    private HLayout layoutPage5() {
        HLayout page = new HLayout();
        page.setWidth100();
        page.setHeight(395);
        page.setLeft(0);
        page.setTop(0);

        HTMLPane description = new HTMLPane();
        description.setWidth(DESCRIPTION_WIDTH);
        description.setHeight(395);
        description
            .setContents("Customize your captain using this wizard, including setting images, and attributes. &nbsp;"
                + "On this first page, select a captain name that will be unique across the entire game system.");
        description.setMargin(15);

        VLayout pageRight = new VLayout(10);
        pageRight.setHeight(395);
        pageRight.setWidth100();

        summaryNameItem.setDefaultValue((String) getNameTextItem().getValue());
        summaryNameItem.setDisabled(true);
        summaryNameItem.setStartRow(true);
        summaryNameItem.setColSpan(3);

        summaryColorPicker.setDefaultValue((String) this.getColorPicker().getValue());
        summaryColorPicker.setDisabled(true);
        summaryColorPicker.setStartRow(true);

        summaryNegotiationItem.setWidth(50);
        summaryNegotiationItem.setDefaultValue((String) getNameTextItem().getValue());
        summaryNegotiationItem.setDisabled(true);

        summaryStorageItem.setWidth(50);
        summaryStorageItem.setDefaultValue((String) getNameTextItem().getValue());
        summaryStorageItem.setDisabled(true);

        summaryNavigationItem.setWidth(50);
        summaryNavigationItem.setDefaultValue((String) getNameTextItem().getValue());
        summaryNavigationItem.setDisabled(true);

        summaryShieldsItem.setWidth(50);
        summaryShieldsItem.setDefaultValue((String) getNameTextItem().getValue());
        summaryShieldsItem.setDisabled(true);

        summaryLasersItem.setWidth(50);
        summaryLasersItem.setDefaultValue((String) getNameTextItem().getValue());
        summaryLasersItem.setDisabled(true);

        summaryMissilesItem.setWidth(50);
        summaryMissilesItem.setDefaultValue((String) getNameTextItem().getValue());
        summaryMissilesItem.setDisabled(true);

        summaryEnginesItem.setWidth(50);
        summaryEnginesItem.setDefaultValue((String) getNameTextItem().getValue());
        summaryEnginesItem.setDisabled(true);

        summaryRepairItem.setWidth(50);
        summaryRepairItem.setDefaultValue((String) getNameTextItem().getValue());
        summaryRepairItem.setDisabled(true);

        summaryDefenseItem.setWidth(50);
        summaryDefenseItem.setDefaultValue((String) getNameTextItem().getValue());
        summaryDefenseItem.setDisabled(true);

        summaryIntelligenceItem.setWidth(50);
        summaryIntelligenceItem.setDefaultValue((String) getNameTextItem().getValue());
        summaryIntelligenceItem.setDisabled(true);
        summaryIntelligenceItem.setStartRow(true);

        summaryKnowledgeItem.setWidth(50);
        summaryKnowledgeItem.setDefaultValue((String) getNameTextItem().getValue());
        summaryKnowledgeItem.setDisabled(true);

        DynamicForm summaryForm = new DynamicForm();
        summaryForm.setFields(summaryNameItem, summaryColorPicker);

        DynamicForm skillForm = new DynamicForm();
        skillForm.setNumCols(4);
        skillForm.setColWidths("25%", "25%", "25%", "25%");
        skillForm.setIsGroup(true);
        skillForm.setGroupTitle("Skills");
        skillForm.setWidth(390);

        skillForm.setFields(summaryNegotiationItem, summaryStorageItem, summaryNavigationItem, summaryShieldsItem,
            summaryLasersItem, summaryMissilesItem, summaryEnginesItem, summaryRepairItem, summaryDefenseItem);

        DynamicForm attributeForm = new DynamicForm();
        attributeForm.setNumCols(4);
        attributeForm.setColWidths("25%", "25%", "25%", "25%");
        attributeForm.setIsGroup(true);
        attributeForm.setGroupTitle("Attributes");
        attributeForm.setWidth(390);

        attributeForm.setFields(summaryIntelligenceItem, summaryKnowledgeItem);

        Label topSpacer = new Label();
        topSpacer.setHeight(10);
        topSpacer.setWidth(5);

        pageRight.addMember(topSpacer);
        pageRight.addMember(summaryForm);
        pageRight.addMember(skillForm);
        pageRight.addMember(attributeForm);

        page.addMember(description);
        page.addMember(pageRight);

        pages[5] = page;
        return page;
    }

    /**
     * 
     * @return the third page
     */
    private HLayout layoutPage3() {
        HLayout page = new HLayout();
        page.setWidth100();
        page.setHeight(395);
        page.setLeft(0);
        page.setTop(0);

        HTMLPane description = new HTMLPane();
        description.setWidth(DESCRIPTION_WIDTH);
        description.setHeight(395);
        description
            .setContents("Customize your captain using this wizard, including setting images, and attributes. &nbsp;"
                + "On this first page, select a captain name that will be unique across the entire game system.");
        description.setMargin(15);

        VLayout pageRight = new VLayout();
        pageRight.setHeight(395);
        pageRight.setWidth100();

        // TODO pull specifications from storage
        HTMLPane htmlPane = new HTMLPane();
        String contents =
            "<div><b>Summary</b></div>"
                + "You have been selected as a new captain to have one of the best ships in the industry.  "
                + "The <b>Sparrow</b> is one of the most reliable ships because of its maturity.  "
                + "Created some 200 years ago, these ships have seen all corners of known space.  The specifications on the <b>Sparrow</b> are:"
                + "<table border=\"1\">"
                + "<tbody>"
                + "<tr>"
                + "<td>Attribute</td>"
                + "<td>Value</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Shields</td>"
                + "<td style=\"text-align: right;\">6<span></span></td>"
                + "</tr>"
                + "<tr>"
                + "<td>Computer</td>"
                + "<td style=\"text-align: right;\">2&nbsp;</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Storage</td>"
                + "<td style=\"text-align: right;\">&nbsp;15</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Lasers&nbsp;</td>"
                + "<td style=\"text-align: right;\">&nbsp;2</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Missiles&nbsp;</td>"
                + "<td style=\"text-align: right;\">&nbsp;0</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Reactive Engines&nbsp;</td>"
                + "<td style=\"text-align: right;\">&nbsp;12</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Jump Engines&nbsp;</td>"
                + "<td style=\"text-align: right;\">&nbsp;1</td>"
                + "</tr>"
                + "</tbody>"
                + "</table>"
                + "<br>"
                + "<div><br>"
                + "</div>"
                + "<div><b>Upgrades</b></div>"
                + "<div>The <b>Sparrow</b> does not have many upgrade possibilities. &nbsp;You can purchase additional storage, "
                + "but this will slow down the reactive engines.</div>";

        htmlPane.setContents(contents);

        DynamicForm shipForm = new DynamicForm();
        shipForm.setHeight(30);
        shipForm.setWidth100();
        String shipNamePrompt = "<nobr>The name of your new Sparrow.</nobr>";
        shipNameItem.setHint(shipNamePrompt);
        shipNameItem.setPrompt(shipNamePrompt);
        shipNameItem.setRequired(true);
        shipNameItem.setWidth(200);
        shipForm.setFields(shipNameItem);

        Label topSpacer = new Label();
        topSpacer.setHeight(10);
        topSpacer.setWidth(5);
        pageRight.addMember(topSpacer);
        pageRight.addMember(shipForm);
        pageRight.addMember(htmlPane);

        page.addMember(description);
        page.addMember(pageRight);

        pages[3] = page;
        return page;
    }

    /**
     * 
     * @return the fourth page
     */
    private HLayout layoutPage4() {
        HLayout page = new HLayout();
        page.setWidth100();
        page.setHeight(395);
        page.setLeft(0);
        page.setTop(0);

        HTMLPane description = new HTMLPane();
        description.setWidth(DESCRIPTION_WIDTH);
        description.setHeight(395);
        description.setContents("Customize your captain using this wizard, including setting images, and attributes. &nbsp;"
                + "On this first page, select a captain name that will be unique across the entire game system.");
        description.setMargin(15);

        VLayout pageRight = new VLayout();
        pageRight.setHeight(395);
        pageRight.setWidth100();

        // TODO pull specifications from storage
        String contents =
            "Your new captain and the ship are located at ~planet details~. &nbsp;The planet is located in the ~solar system~ system which "
            + "has &lt;solar system #&gt; of planets and moons. &nbsp;You are currently docked at the spaceport and can visit the market on "
            + "this planet if you wish. &nbsp;Keep in mind that each time you dock, there will be port fees. &nbsp;Since you are a new captain, "
            + "these port fees will be waived for your first departure.";

        htmlLocationPane.setContents(contents);
        Label topSpacer = new Label();
        topSpacer.setHeight(10);
        topSpacer.setWidth(5);
        pageRight.addMember(topSpacer);
        pageRight.addMember(htmlLocationPane);

        page.addMember(description);
        page.addMember(pageRight);

        pages[4] = page;
        return page;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void render() {
        rootWindow.setAutoCenter(true);
        rootWindow.show();

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getCaptainImage()
	 */
    @Override
	public Img getCaptainImage() {
        return captainImage;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getCaptainImageButtonNext()
	 */
    @Override
	public IButton getCaptainImageButtonNext() {
        return captainImageButtonNext;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getCaptainImageButtonPrevious()
	 */
    @Override
	public IButton getCaptainImageButtonPrevious() {
        return captainImageButtonPrevious;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#insertLocation(com.qagwaai.starmalaccamax.client.service.action.GetLocationForNewCaptainResponse)
	 */
    @Override
	public void insertLocation(GetLocationForNewCaptainResponse response) {
        Planet planet = response.getPlanet();
        if (planet != null) {
            SolarSystem solarSystem = response.getSolarSystem();
            String orig = htmlLocationPane.getContents();
            String planetReplaced =
                orig.replaceAll("~planet details~", planet.getName() + "(" + planet.getLocation() + ")");
            String solarSystemReplaced = planetReplaced.replaceAll("~solar system~", solarSystem.getName());
            htmlLocationPane.setContents(solarSystemReplaced);
            shipLocation = response.getPlanet().getLocation();
        } else {
            GWT.log("received null planet for location...");
        }
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getNameForm()
	 */
    @Override
	public DynamicForm getNameForm() {
        return nameForm;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#setNameForm(com.smartgwt.client.widgets.form.DynamicForm)
	 */
    @Override
	public void setNameForm(DynamicForm nameForm) {
        this.nameForm = nameForm;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getTotalSkillAvailable()
	 */
    @Override
	public Label getTotalSkillAvailable() {
        return totalSkillAvailable;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getTotalAttributeAvailable()
	 */
    @Override
	public Label getTotalAttributeAvailable() {
        return totalAttributeAvailable;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getGenderGroupItem()
	 */
    @Override
	public RadioGroupItem getGenderGroupItem() {
        return genderGroupItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getRaceGroupItem()
	 */
    @Override
	public RadioGroupItem getRaceGroupItem() {
        return raceGroupItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getShipNameItem()
	 */
    @Override
	public TextItem getShipNameItem() {
        return shipNameItem;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView#getShipLocation()
	 */
    @Override
	public LocationDTO getShipLocation() {
        return shipLocation;
    }

}
