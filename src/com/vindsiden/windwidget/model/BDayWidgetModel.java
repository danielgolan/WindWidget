package com.vindsiden.windwidget.model;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.net.ParseException;
import android.util.Log;

public class BDayWidgetModel extends APrefWidgetModel 
{
   private static String tag="BDayWidgetModel";
   
   //**************************************************
   //* Give a name to uniquely identify shared preferences   
   //**************************************************   
   private static String BDAY_WIDGET_PROVIDER_NAME=
      "com.ai.android.BDayWidget.BDayWidgetProvider";
   

   //**************************************************
   //* Real model where attributes we are after are   
   //**************************************************   
   private String name = "anon";
   private String bday = "1/1/2001";

   //remember these to set the values back
   private static String F_NAME = "name";
   private static String F_BDAY = "bday";

   //the invariant to buy gifts from   
   private String url="http://www.google.com";
   
   //**************************************************
   //* Construction, basic gets and sets   
   //**************************************************   
   public BDayWidgetModel(int instanceId)
   {
      super(instanceId);
   }
   
   public BDayWidgetModel(int instanceId, String inName, String inBday)
   {
      super(instanceId);
      name=inName;
      bday=inBday;
   }
    public void init()
    {
    }

    public void setName(String inname)
    {
       name=inname;
    }
    public void setBday(String inbday)
    {
       bday=inbday;
    }
    public String getName()
    {
       return name;
    }
    public String getBday()
    {
       return bday;
    }
    
    public long howManyDays()
    {
       try
       {
          return 100; //Utils.howfarInDays(Utils.getDate(this.bday));
       }
       catch(ParseException x)
       {
          return 20000;
       }
    }
   //****************************
   //Implement save contract
   //****************************
    public void setValueForPref(String key, String value)
    {
       if (key.equals(getStoredKeyForFieldName(BDayWidgetModel.F_NAME)))
       {
          Log.d(tag,"Setting name to:" + value);
          this.name = value;
          return;
       }
       if (key.equals(getStoredKeyForFieldName(BDayWidgetModel.F_BDAY)))
       {
          Log.d(tag,"Setting bday to:" + value);
          this.bday = value;
          return;
       }
       Log.d(tag,"Sorry the key does not match:" + key);
    }
    
    //you need to do this
   public String getPrefname()
   {
      return BDayWidgetModel.BDAY_WIDGET_PROVIDER_NAME;
   }
   
   //return key value pairs you want to be saved
   public Map<String,String> getPrefsToSave()
   {
      Map<String, String> map
      = new HashMap<String,String>();
      map.put(BDayWidgetModel.F_NAME, this.name);
      map.put(BDayWidgetModel.F_BDAY, this.bday);
      return map;
   }
   public String toString()
   {
      StringBuffer sbuf = new StringBuffer();
      sbuf.append("iid:" + iid);
      sbuf.append("name:" + name);
      sbuf.append("bday:" + bday);
      return sbuf.toString();
   }
   public static void clearAllPreferences(Context ctx)
   {
      APrefWidgetModel.clearAllPreferences(ctx,BDayWidgetModel.BDAY_WIDGET_PROVIDER_NAME);
   }
   
   public static BDayWidgetModel retrieveModel(Context ctx, int widgetId)
   {
      BDayWidgetModel m = new BDayWidgetModel(widgetId);
      boolean found = m.retrieve(ctx);//m.retrievePrefs(ctx);
      return found ? m:null; 
   }
}