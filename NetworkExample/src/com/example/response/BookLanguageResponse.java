package com.example.response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
;


public class BookLanguageResponse {

	private JSONObject jObject;

	private BookInfo [] bookInfos;
	private String [] booklanguages;
	
	public String[] getBooklanguages() {
		return booklanguages;
	}

	public BookInfo[] getBooks(){
		return bookInfos;
	}

	public static class BookInfo {
		private String id;
		private String language;
		private String story_id;
		private String title;
	
	
	public String getStory_id() {
			return story_id;
		}

		public void setStory_id(String story_id) {
			this.story_id = story_id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}


		public void setLanguage(String language) {
			this.language = language;
		}

	public String getid() {
		return id;
	}
	
	public String getLanguage() {
		return language;
	}

	}

	public BookLanguageResponse(byte[] data) throws JSONException {


		JSONArray array = new JSONArray(new String(data));

		bookInfos = new BookInfo[array.length()];
		
		for( int i = 0 ; i < bookInfos.length; i++){

			jObject = array.getJSONObject(i);

			BookInfo book = new BookInfo();
			book.id = jObject.getString("story_id");
			book.language = jObject.getString("language");
			
			
			bookInfos[i] = book;
		
	}
	
	
	}
}
