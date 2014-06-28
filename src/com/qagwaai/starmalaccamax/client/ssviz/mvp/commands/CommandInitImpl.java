package com.qagwaai.starmalaccamax.client.ssviz.mvp.commands;

import com.google.gwt.user.client.Command;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.GetPlanetsForSolarSystem;
import com.qagwaai.starmalaccamax.client.service.action.GetPlanetsForSolarSystemResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetPlanetsForSolarSystemResponseInJSON;
import com.qagwaai.starmalaccamax.client.service.action.GetSolarSystem;
import com.qagwaai.starmalaccamax.client.service.action.GetSolarSystemResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetSolarSystemResponseInJSON;
import com.qagwaai.starmalaccamax.client.service.action.GetStarsForSolarSystem;
import com.qagwaai.starmalaccamax.client.service.action.GetStarsForSolarSystemResponse;
import com.qagwaai.starmalaccamax.client.service.action.GetStarsForSolarSystemResponseInJSON;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.qagwaai.starmalaccamax.client.ssviz.jsni.Bridge;
import com.qagwaai.starmalaccamax.client.ssviz.mvp.widget.SolarSystemVisualizationView;
import com.qagwaai.starmalaccamax.shared.MimeType;

public final class CommandInitImpl implements Command {

    private final String elementId;
    private final SolarSystemVisualizationView view;

    public CommandInitImpl(final String elementId, final SolarSystemVisualizationView view) {
	super();
	this.elementId = elementId;
	this.view = view;
    }

    @Override
    public void execute() {
	Bridge.callStartupInit(elementId, view.getRootHeight(), view.getRootWidth());

	ServiceFactory.getPlanetService().execute(new GetPlanetsForSolarSystem(MimeType.js, 1049L), new BaseAsyncCallback<GetPlanetsForSolarSystemResponse>() {

	    @Override
	    public void onFailure(final Throwable caught) {
		super.onFailure(caught);
		view.say("Error", "Could not load solar system: " + caught.getMessage());
	    }

	    @Override
	    public void onSuccess(final GetPlanetsForSolarSystemResponse result) {
		super.onSuccess(result);
		String jsonPlanets = ((GetPlanetsForSolarSystemResponseInJSON) result).getPlanets();
		Bridge.setAllPlanets(jsonPlanets);
	    }
	});
	GetSolarSystem action = new GetSolarSystem(MimeType.js, 1049L);
	ServiceFactory.getSolarSystemService().execute(action, new BaseAsyncCallback<GetSolarSystemResponse>() {

	    @Override
	    public void onFailure(final Throwable caught) {
		super.onFailure(caught);
		view.say("Error", "Could not load solar system: " + caught.getMessage());
	    }

	    @Override
	    public void onSuccess(final GetSolarSystemResponse result) {
		super.onSuccess(result);

		Bridge.setSolarSystem(((GetSolarSystemResponseInJSON) result).getSolarSystem());
	    }
	});
	
	ServiceFactory.getStarService().execute(new GetStarsForSolarSystem(MimeType.js, 1049L), new BaseAsyncCallback<GetStarsForSolarSystemResponse>() {

	    @Override
	    public void onFailure(final Throwable caught) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void onSuccess(final GetStarsForSolarSystemResponse result) {
		super.onSuccess(result);
		Bridge.setStars(((GetStarsForSolarSystemResponseInJSON) result).getStars());
	    }
	});

    }
}
