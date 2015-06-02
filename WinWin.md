# Introduction #

WinWin provides key-value pair storage with optional authentication. The api is
  * get
  * put
  * append
  * copy
Google App Engine quotas limit an individual upload to <10MB and for a HTTP Post parameter to 200K. The _append_ method is useful in this case.

Given that updating a key can involve multiple uploads (via _put_ and _append_), you may want to update a temporary key in this way and then use the _copy_ method to move the information to the key you want to update.

# Authentication #

A call to get/put/append/copy may bring about an authentication request in these circumstances:

Administrator authentication via Google Apps login
  * key starts with _authentication_
  * key starts with _secure_
Basic Http authentication (optionally over https) prompting for a username and password
  * key starts with _authenticated_