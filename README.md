# W2 Assignment - New York News Search

New York News Search is an android app which allows a user to find old articles.

Submitted by: Tan Le Ngoc Minh

Time spent: 15 hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can enter a search query that will display a grid of news articles using the thumbnail and headline from the New York Times Search API
* [x] User can click on "settings" which allows selection of advanced search options to filter results
* [x] User can configure advanced search filters such as:
 - Begin Date (using a date picker)
 - News desk values
 - Sort order
* [x] Subsequent searches will have any filters applied to the search results
* [x] User can tap on any article in results to view the contents in an embedded browser
* [x] User can scroll down "infinitely" to continue loading more news articles. The maximum number of articles is limited by the API search

The following **optional** features are implemented:

* [x] Robust error handling, check if internet is available, handle error cases, network failures
* [x] Use the ActionBar SearchView or custom layout as the query box instead of an EditText
* [x] Improve the user interface and experiment with image assets and/or styling and coloring
* [x] Use the RecyclerView with the StaggeredGridLayoutManager to display improve the grid of image results
* [x] For different news articles that only have text or have text with thumbnails, use Heterogenous Layouts with RecyclerView
* [x] Apply the popular ButterKnife annotation library to reduce view boilerplate
* [x] Leverage the popular GSON library to streamline the parsing of JSON data
* [x] Replace Picasso with Glide for more efficient image rendering
* [ ] User can share a link to their friends or email it to themselves
* [x] Replace Filter Settings Activity with a Fragment
* [ ] Use Parcelable instead of Serializable using the popular Parceler library

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/UAVibZ6.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

## License

    Copyright 2016 TanLNM

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
