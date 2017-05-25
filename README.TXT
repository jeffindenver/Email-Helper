Title: Email Helper

Purpose: This program allows the user to organize accounts and associated emails then quickly load the template emails into the system clipboard to be pasted into an outgoing email.



ACCOUNTS: The email helper is organized by accounts. The drop down combo boxes are named after the name of the of the folders contained in the "accounts" directory. For example, if the accounts directory held a folder named "EMS," the title of the combo box appearing in the application would be "EMS." The text file keys.txt inside the account folder contains the keywords that will appear in the drop down menu. Other files contained there define the URL where files are located (if any) and a text file that lists the filenames of all the associated template emails.



INSIDE ACCOUNTS FOLDER:
The entries in each of the following files are parallel, meaning the first in filenames.txt corresponds with the first in keys.txt, which both correspond to the first entry in urlList.txt.

filenames.txt -- lists each filename of the template emails.
	
keys.txt -- contains the keywords that will appear in the drop down menu of the corresponding account.

urlList.txt -- contains an optional list of the urls for the email templates. This allows templates to be changed for all users with a single edit. Alternatively, users can customize their email templates by placing a copy of the template in the associated account folder.

ADDING & REMOVING ACCOUNTS:
No more than four accounts will appear in the application at the same time. Any beyond four will be ignored. To add an account, copy an existing folder and rename it to the new account. Then customize each of the contained files. To remove an account, simply delete the folder.
