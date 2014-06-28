/**
 * NewCaptainPresenter.java
 * Created by pgirard at 2:29:00 PM on Aug 10, 2010
 * in the com.qagwaai.starmalaccamax.client.presenter package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp;

import com.qagwaai.starmalaccamax.client.core.LoginWatcher;
import com.qagwaai.starmalaccamax.client.core.mvp.AbstractPresenter;
import com.qagwaai.starmalaccamax.client.event.EventBus;
import com.qagwaai.starmalaccamax.client.game.callbacks.GetCaptainLocationAsyncCallbackImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.CaptainImageNextClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.CaptainImagePreviousClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.CaptainNameChangedHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.NewCaptainAttributeValueChangedHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.NewCaptainNextClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.NewCaptainPreviousClickHandlerImplementation;
import com.qagwaai.starmalaccamax.client.game.mvp.handlers.NewCaptainSkillValueChangedHandlerImplementation;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.AddCaptain;
import com.qagwaai.starmalaccamax.client.service.action.AddShip;
import com.qagwaai.starmalaccamax.client.service.action.GetLocationForNewCaptain;
import com.qagwaai.starmalaccamax.client.service.helpers.AddedCaptain;
import com.qagwaai.starmalaccamax.client.service.helpers.AddedShip;
import com.qagwaai.starmalaccamax.shared.model.Captain;
import com.qagwaai.starmalaccamax.shared.model.CaptainDTO;
import com.qagwaai.starmalaccamax.shared.model.Ship;
import com.qagwaai.starmalaccamax.shared.model.ShipAttributes;
import com.qagwaai.starmalaccamax.shared.model.ShipDTO;
import com.qagwaai.starmalaccamax.shared.model.ShipTravelStatus;

/**
 * @author pgirard
 * 
 */
public final class NewCaptainPresenterImpl extends AbstractPresenter<NewCaptainView, Captain> implements NewCaptainPresenter {


    /**
	 * 
	 */
    public static final int TOTAL_SKILL_POINTS = 20;

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainPresenter#getCaptainImage()
	 */
    @Override
	public int getCaptainImage() {
		return captainImage;
	}

	/* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainPresenter#setCaptainImage(int)
	 */
	@Override
	public void setCaptainImage(int captainImage) {
		this.captainImage = captainImage;
	}

	/**
	 * 
	 */
    public static final int TOTAL_ATTRIBUTE_POINTS = 10;

    private int captainImage = 1;

    /**
     * 
     * @param eventBus
     *            the bus to publish events on
     * @param view
     *            the view that this presenter will attach to
     * @param model
     *            the model to present, if any
     */
    public NewCaptainPresenterImpl(final EventBus eventBus, final NewCaptainViewImpl view, final Captain model) {
        super(eventBus, view, model);
        view.setPresenter(this);

        view.layout();

        view.getNextButton().addClickHandler(new NewCaptainNextClickHandlerImplementation(view, this));
        view.getPreviousButton().addClickHandler(new NewCaptainPreviousClickHandlerImplementation(view));

        NewCaptainSkillValueChangedHandlerImplementation skillCheckHandler = new NewCaptainSkillValueChangedHandlerImplementation(view, this);
        view.getNegotiationItem().addValueChangedHandler(skillCheckHandler);
        view.getNavigationItem().addValueChangedHandler(skillCheckHandler);
        view.getShieldsItem().addValueChangedHandler(skillCheckHandler);
        view.getLasersItem().addValueChangedHandler(skillCheckHandler);
        view.getMissilesItem().addValueChangedHandler(skillCheckHandler);
        view.getEnginesItem().addValueChangedHandler(skillCheckHandler);
        view.getRepairItem().addValueChangedHandler(skillCheckHandler);
        view.getDefenseItem().addValueChangedHandler(skillCheckHandler);
        view.getStorageItem().addValueChangedHandler(skillCheckHandler);

        NewCaptainAttributeValueChangedHandlerImplementation attributeCheckHandler =
            new NewCaptainAttributeValueChangedHandlerImplementation(view, this);
        view.getIntelligenceItem().addValueChangedHandler(attributeCheckHandler);
        view.getKnowledgeItem().addValueChangedHandler(attributeCheckHandler);
        view.getNameTextItem().addChangedHandler(new CaptainNameChangedHandlerImplementation(view));
        view.getCaptainImage().setSrc("captains/captain" + captainImage + ".png");
        view.getCaptainImageButtonNext().addClickHandler(new CaptainImageNextClickHandlerImplementation(view, this));
        view.getCaptainImageButtonPrevious().addClickHandler(new CaptainImagePreviousClickHandlerImplementation(view, this));
        ServiceFactory.getSolarSystemService().execute(new GetLocationForNewCaptain(),
            new GetCaptainLocationAsyncCallbackImplementation(view));
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void destroyView() {
        getView().destroy();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void hideView() {
        getView().hide();

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void renderView() {
        getView().render();
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainPresenter#saveNewCaptain()
	 */
    @Override
	public void saveNewCaptain() {
        Captain captain = new CaptainDTO();

        captain.setName((String) getView().getNameTextItem().getValue());
        captain.setColor((String) getView().getColorPicker().getValue());
        captain.setGender(getView().getGenderGroupItem().getValueAsString());
        captain.setRace(getView().getRaceGroupItem().getValueAsString());
        captain.setOwnerId(LoginWatcher.getInstance().getLastEvent().getCurrentUser().getId());
        captain.getSkills().setDefense(Math.round(getView().getDefenseItem().getValue()));
        captain.getSkills().setEngines(Math.round(getView().getEnginesItem().getValue()));
        captain.getSkills().setLasers(Math.round(getView().getLasersItem().getValue()));
        captain.getSkills().setMissiles(Math.round(getView().getMissilesItem().getValue()));
        captain.getSkills().setNavigation(Math.round(getView().getNavigationItem().getValue()));
        captain.getSkills().setNegotiation(Math.round(getView().getNegotiationItem().getValue()));
        captain.getSkills().setRepair(Math.round(getView().getRepairItem().getValue()));
        captain.getSkills().setShields(Math.round(getView().getShieldsItem().getValue()));
        captain.getSkills().setStorage(Math.round(getView().getStorageItem().getValue()));
        captain.getCaptainAttributes().setIntelligence(Math.round(getView().getIntelligenceItem().getValue()));
        captain.getCaptainAttributes().setKnowledge(Math.round(getView().getKnowledgeItem().getValue()));

        ServiceFactory.getCaptainService().execute(new AddCaptain((CaptainDTO) captain), new AddedCaptain() {

            @Override
            public void got(final CaptainDTO captain) {
                Ship ship = new ShipDTO();
                ship.setName(getView().getShipNameItem().getValueAsString());
                ship.setOwnerId(captain.getId());
                ship.setLocation(getView().getShipLocation());
                ship.setShipTravelStatus(ShipTravelStatus.Docked);
                ShipAttributes attributes = new ShipAttributes(6, 2, 15, 2, 0, 12, 1);
                ship.setShipAttributes(attributes);
                ServiceFactory.getShipService().execute(new AddShip((ShipDTO) ship), new AddedShip() {

                    @Override
                    public void got(ShipDTO ship) {
                        getView().closeWindow();
                    }
                });

            }
        });

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void showView() {
        getView().show();

    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainPresenter#sumAttributesSpent()
	 */
    @Override
	public int sumAttributesSpent() {
        int total = 0;
        total += Math.round(getView().getIntelligenceItem().getValue());
        total += Math.round(getView().getKnowledgeItem().getValue());
        return total;
    }

    /* (non-Javadoc)
	 * @see com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainPresenter#sumSkillsSpent()
	 */
    @Override
	public int sumSkillsSpent() {
        int total = 0;
        total += Math.round(getView().getDefenseItem().getValue());
        total += Math.round(getView().getNegotiationItem().getValue());
        total += Math.round(getView().getStorageItem().getValue());
        total += Math.round(getView().getNavigationItem().getValue());
        total += Math.round(getView().getShieldsItem().getValue());
        total += Math.round(getView().getLasersItem().getValue());
        total += Math.round(getView().getMissilesItem().getValue());
        total += Math.round(getView().getEnginesItem().getValue());
        total += Math.round(getView().getRepairItem().getValue());

        return total;
    }

}
