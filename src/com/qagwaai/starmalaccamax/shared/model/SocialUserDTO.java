package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;

/**
 * The object we send back and forth between client and server.
 * Note: More information is stored in server side in ServersideSession
 * object.
 * @author muquit@muquit.com
 */
public class SocialUserDTO implements Serializable
{
    private static final long serialVersionUID=1011L;
    private String sessionId;
    
    private String email;
    private String json;
    
    /* must be named exactly as JSON google returns -- starts */
    private String id;
    private String name;        // full name
    private String given_name;  // first name
    private String family_name; // last name
    private String gender;      // same on Yahoo   
    private String link;
    private String locale;
    /* must be named exactly as JSON google returns -- ends */
    
    /* Yahoo --starts */
    private String guid; // is it always the same for the user??
    private String givenName;
    private String familyName;
    private String nickname;
    private String location;
    private String birthdate;
    private String timeZone;
    private String lang;
    private String relationShipStatus;
    private int    displayAge;
    /* Yahoo --ends */
    
    /* Linkedin -starts */
    private String firstName;
    private String lastName;
    private String headline;
    /* Linkedin -starts */
    
    
    /*
     Google returns JSON like:
     
      {
          "id": "116397076041912827850", 
          "name": "Muhammad Muquit", 
          "given_name": "Muhammad", 
          "family_name": "Muquit", 
          "link": "https://plus.google.com/116397076041912827850", 
          "gender": "male", 
          "locale": "en-US"
       }
     */
    /*
     * http://developer.yahoo.com/social/rest_api_guide/extended-profile-resource.html#
     Yahoo returns JSON like:
{
  "profile": {
    "uri": "http:\/\/social.yahooapis.com\/v1\/user\/ECUFIYO7BLY5FOV54XAPEQDC3Y\/profile",
    "guid": "ECUFIYO7BLY5FOAPEQDC3Y",
    "birthYear": 1969,
    "created": "2010-01-23T13:07:10Z",
    "displayAge": 43,
    "gender": "M",
    "image": {
      "height": 192,
      "imageUrl": "http:\/\/l.yimg.com\/a\/i\/identity2\/profile_192c.png",
      "size": "192x192",
      "width": 192
    },
    "location": "Philadelphia, Pennsylvania",
    "memberSince": "2006-08-04T13:27:58Z",
    "nickname": "jdoe",
    "profileUrl": "http:\/\/profile.yahoo.com\/ECUFIYO7BLY5FOV54XAPEQDC3Y",
    "searchable": false,
    "updated": "2011-04-16T07:28:00Z",
    "isConnected": false
  }
}
     */
    
    
    
    public String getSessionId()
    {
        return sessionId;
    }
    public void setSessionId(String sessionId)
    {
        this.sessionId=sessionId;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public String getJson()
    {
        return json;
    }
    public void setJson(String json)
    {
        this.json=json;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id=id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getGiven_name()
    {
        return given_name;
    }
    public void setGiven_name(String given_name)
    {
        this.given_name=given_name;
    }
    public String getFamily_name()
    {
        return family_name;
    }
    public void setFamily_name(String family_name)
    {
        this.family_name=family_name;
    }
    public String getGender()
    {
        return gender;
    }
    public void setGender(String gender)
    {
        this.gender=gender;
    }
    public String getLink()
    {
        return link;
    }
    public void setLink(String link)
    {
        this.link=link;
    }
    public String getLocale()
    {
        return locale;
    }
    public void setLocale(String locale)
    {
        this.locale=locale;
    }
    public String getGuid()
    {
        return guid;
    }
    public void setGuid(String guid)
    {
        this.guid=guid;
    }
    public String getGivenName()
    {
        return givenName;
    }
    public void setGivenName(String givenName)
    {
        this.givenName=givenName;
    }
    public String getFamilyName()
    {
        return familyName;
    }
    public void setFamilyName(String familyName)
    {
        this.familyName=familyName;
    }
    public String getNickname()
    {
        return nickname;
    }
    public void setNickname(String nickname)
    {
        this.nickname=nickname;
    }
    public String getLocation()
    {
        return location;
    }
    public void setLocation(String location)
    {
        this.location=location;
    }
    public String getBirthdate()
    {
        return birthdate;
    }
    public void setBirthdate(String birthdate)
    {
        this.birthdate=birthdate;
    }
    public String getTimeZone()
    {
        return timeZone;
    }
    public void setTimeZone(String timeZone)
    {
        this.timeZone=timeZone;
    }
    public String getLang()
    {
        return lang;
    }
    public void setLang(String lang)
    {
        this.lang=lang;
    }
    public String getRelationShipStatus()
    {
        return relationShipStatus;
    }
    public void setRelationShipStatus(String relationShipStatus)
    {
        this.relationShipStatus=relationShipStatus;
    }
    public int getDisplayAge()
    {
        return displayAge;
    }
    public void setDisplayAge(int displayAge)
    {
        this.displayAge=displayAge;
    }
    
    /* linkedin */ 
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName=firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName=lastName;
    }
    public String getHeadline()
    {
        return headline;
    }
    public void setHeadline(String headline)
    {
        this.headline=headline;
    }
    /* linkedin */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + displayAge;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((familyName == null) ? 0 : familyName.hashCode());
		result = prime * result
				+ ((family_name == null) ? 0 : family_name.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result
				+ ((givenName == null) ? 0 : givenName.hashCode());
		result = prime * result
				+ ((given_name == null) ? 0 : given_name.hashCode());
		result = prime * result + ((guid == null) ? 0 : guid.hashCode());
		result = prime * result
				+ ((headline == null) ? 0 : headline.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((json == null) ? 0 : json.hashCode());
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		result = prime
				* result
				+ ((relationShipStatus == null) ? 0 : relationShipStatus
						.hashCode());
		result = prime * result
				+ ((sessionId == null) ? 0 : sessionId.hashCode());
		result = prime * result
				+ ((timeZone == null) ? 0 : timeZone.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SocialUserDTO other = (SocialUserDTO) obj;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (displayAge != other.displayAge)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (familyName == null) {
			if (other.familyName != null)
				return false;
		} else if (!familyName.equals(other.familyName))
			return false;
		if (family_name == null) {
			if (other.family_name != null)
				return false;
		} else if (!family_name.equals(other.family_name))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (givenName == null) {
			if (other.givenName != null)
				return false;
		} else if (!givenName.equals(other.givenName))
			return false;
		if (given_name == null) {
			if (other.given_name != null)
				return false;
		} else if (!given_name.equals(other.given_name))
			return false;
		if (guid == null) {
			if (other.guid != null)
				return false;
		} else if (!guid.equals(other.guid))
			return false;
		if (headline == null) {
			if (other.headline != null)
				return false;
		} else if (!headline.equals(other.headline))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (json == null) {
			if (other.json != null)
				return false;
		} else if (!json.equals(other.json))
			return false;
		if (lang == null) {
			if (other.lang != null)
				return false;
		} else if (!lang.equals(other.lang))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (relationShipStatus == null) {
			if (other.relationShipStatus != null)
				return false;
		} else if (!relationShipStatus.equals(other.relationShipStatus))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (timeZone == null) {
			if (other.timeZone != null)
				return false;
		} else if (!timeZone.equals(other.timeZone))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SocialUserDTO [sessionId=" + sessionId + ", email=" + email
				+ ", json=" + json + ", id=" + id + ", name=" + name
				+ ", given_name=" + given_name + ", family_name=" + family_name
				+ ", gender=" + gender + ", link=" + link + ", locale="
				+ locale + ", guid=" + guid + ", givenName=" + givenName
				+ ", familyName=" + familyName + ", nickname=" + nickname
				+ ", location=" + location + ", birthdate=" + birthdate
				+ ", timeZone=" + timeZone + ", lang=" + lang
				+ ", relationShipStatus=" + relationShipStatus
				+ ", displayAge=" + displayAge + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", headline=" + headline + "]";
	}
    
    
}

