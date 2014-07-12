package tme.transactthis.com.couponquest.model.inmar.vo;

/**
 * Created by trey on 7/12/14.
 * Copyright 2014 MindMine LLC.
 */
public class OfferParams {


    public OfferParams(String token
            , int skip
            , boolean clipped
            , String keywords
            , int limit
            , String categories
            , String sort
            , boolean redeemed
            , boolean expired
            , boolean includeClipped) {
        this.token = token;
        this.skip = skip;
        this.clipped = clipped;
        this.keywords = keywords;
        this.limit = limit;
        this.categories = categories;
        this.sort = sort;
        this.redeemed = redeemed;
        this.expired = expired;
        this.includeClipped = includeClipped;
    }

    //    Include the user token to retrieve a customized list of offers, specific to that user - by
    //    default unclipped (available) offers are returned first, followed by any clipped offers
    //    unless clipped is set to false
    public String token;

    //skip specified number of single offers (for paging)
    public int skip;

    //set to 'true' to return a user's clipped offers only (must be passed in with a token)
    public boolean clipped;

    //keywords a comma delimited list of keywords to search offers for (e.g. "baby,food")
    public String keywords;

    //limit to n number of offers (default is 12)
    public int limit;

    //categories comma delimited list of category ids to return (from the category API), default is offers
    //from all categories
    public String categories;

    //sort string for sort order (asc | desc)
    public String sort;

    //redeemed set to 'true' to retrieve a user's redeemed offers (token must also be set), clipped/expired
    //must not be set - sets redeemedDate JSON element for date redeemed (null otherwise)
    public boolean redeemed;

    //expired set to 'true' to retrieve a user's expired offers (token must also be set), clipped/redeemed
    //must not be set
    public boolean expired;

    //includeClipped set to 'false' to exclude clipped offers (overrides clipped)
    public boolean includeClipped;
}
