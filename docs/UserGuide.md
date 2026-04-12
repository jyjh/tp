---
layout: page
title: User Guide
---

DraftDeck is a **desktop app for managing gaming teams and players, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, DraftDeck can get your team management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

2. Download the latest `.jar` file from [here](https://github.com/AY2526S2-CS2103T-W09-2/tp/releases). Optionally, also download the images.

3. Copy the file to the folder you want to use as the _home folder_ for your DraftDeck.

<div markdown="span" class="alert alert-warning">:exclamation: **Important:**
Ensure the folder is **not write-protected**. DraftDeck will not work properly if placed in a write-protected directory, as it needs to create data files and configuration files.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you wish to use images, place the downloaded images folder here as well!
</div>

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar draftdeck.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

<div markdown="span" class="alert alert-warning">:exclamation: **Note:**
Double-clicking the jar file might not work on some systems. If this happens, use the command line method: open Command Prompt/Terminal, navigate to the folder containing the jar file, and type `java -jar draftdeck.jar`.
</div>

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all players.

   * `add n/John Doe p/98765432 e/johnd@example.com i/JohnD88 r/MID rank/GOLD I` : Adds a player named `John Doe` to the player list.

   * `delete 3` : Deletes the 3rd indexed player.

   * `clear` : Deletes all players.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
First time using this app? Consider going through the [walkthrough](#walkthrough-setting-up-the-national-esports-team) first to get a feel for things!
</div>

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items in round brackets are required, but are in an either|or format.<br>
  e.g `(INDEX | i/IGN)` can be `1` or `i/Player1`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* In this guide, `INDEX` means the index number shown beside each player in the list.<br>
  These numbers are global: they are based on the full player list and stay the same after `find`/`filter`.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Core Commands

#### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### Clearing all entries : `clear`

Clears all entries from the player list.

Format: `clear`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Player management

#### Adding a player: `add`

Adds a player to the player list. The player added must be unique.

Format: `add n/NAME p/PHONE e/EMAIL i/IGN r/ROLE rank/RANK [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A player can have any number of tags (including 0)
</div>
<div markdown="1" class="alert alert-primary"> **What counts as a unique player?**
<br>
* The player's IGN must not match IGNs that are already present in the app.
* Note that IGNs are not case-sensitive and limited to 16 characters (to comply with League of Legends' IGN length limit).  
* Players with the same name but different IGNs are considered unique, since different players may have the same name, while the uniqueness of IGNs is enforced by the game.
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com i/JohnD88 r/MID rank/GOLD II`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com i/Betsycrowe r/BOT rank/PLATINUM I p/1234567`

#### Deleting a player : `delete`

Deletes the specified player from the player list.

Format: `delete INDEX`

* Deletes the player at the specified `INDEX`.
* The index refers to the index number shown beside each player in the list.
* These numbers are global: they are based on the full player list and stay the same after `find`/`filter`.
* The index **must be a positive integer** 1, 2, 3, …​

#### Editing a player : `edit`

Edits an existing player in the player list.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [i/IGN] [r/ROLE] [rank/RANK] [t/TAG]…​`

* Edits the player at the specified `INDEX`. The index refers to the index number shown beside each player in the list.
* These numbers are global: they are based on the full player list and stay the same after `find`/`filter`.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the player will be removed i.e adding of tags is not cumulative.
* You can remove all the player's tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st player to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd player to be `Betsy Crower` and clears all existing tags.
*  `edit 3 r/JUNGLE rank/DIAMOND I` Edits the role and rank of the 3rd player.

#### Listing all players : `list`

Shows a list of all players in the player list.

Format: `list`

### Search and Discovery

#### Locating players by name: `find`

Finds players whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Players matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
* Changes which players are displayed, but the index numbers stay the same.
* These numbers are global: they are based on the full player list and stay the same after `find`/`filter`.

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

#### Filtering players : `filter`

Finds players whose tags, roles, or entities contain any of the given keywords.

Format: `filter [t/KEYWORD [MORE_KEYWORDS]...] [r/KEYWORD [MORE_KEYWORDS]...] [ent/KEYWORD [MORE_KEYWORDS]...]`

* The search is case-insensitive. e.g `friend` will match `friend`, `Friend`, or `FRIEND`
* You can filter by tags (`t/`), roles (`r/`), entities (`ent/`), or any combination of these.
* Within each category (tags, roles, entities), players matching at least one keyword will be returned (i.e. `OR` search).
* Multiple categories are combined with `AND` logic - a player must match at least one keyword from each specified category.
* Only full words will be matched e.g. `friend` will not match `friends`
* Changes which players are displayed, but the index numbers stay the same.
* These numbers are global: they are based on the full player list and stay the same after `find`/`filter`.

Examples:
* `filter t/friend` Returns people tagged with `friend`
* `filter r/top r/jungle` Returns people with role `TOP` or `JUNGLE`
* `filter ent/Ahri ent/Yasuo` Returns people who have statistics for entity `Ahri` or `Yasuo`
* `filter t/pro r/bot ent/Jinx` Returns people who are tagged `pro`, have role `BOT`, AND have statistics for entity `Jinx`

### Sports and Analytics

#### Comparing players : `compare`

Compares two players identified by index or IGN.

Format: `compare (INDEX1 | i/IGN1) (INDEX2 | i/IGN2)`

* Displays details of both players side by side.
* The indices refer to the index numbers shown beside each player in the list.
* These numbers are global: they are based on the full player list and stay the same after `find`/`filter`.
* Indices **must be positive integers** 1, 2, 3, …​
* The two players must be different.
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Clicking on an entity icon in the comparison screen compares statistics for that specific entity.

Click on the same entity icon again to revert to overall statistics comparison.
</div>
Example:
* `compare 1 2` Compares the 1st and 2nd indexed players in the current list.
* `compare i/AlexY42 2` Compares the player with the IGN AlexY42 and the 2nd indexed player in the current list.

#### Drafting a team : `draft`

Validates a 5-player team composition.

Format: `draft (INDEX | i/IGN) (INDEX | i/IGN) (INDEX | i/IGN) (INDEX | i/IGN) (INDEX | i/IGN)`

* Selects exactly 5 distinct players by index number or in-game name (IGN).
* If an index is used, it refers to the index number shown beside each player in the list.
* These numbers are global: they are based on the full player list and stay the same after `find`/`filter`.
* You can mix indices and IGNs in the same command.
* The `i/` prefix can optionally be omitted for non-numeric IGNs (e.g., `PlayerA` instead of `i/PlayerA`).
* A valid team composition has exactly one player per role (TOP, JUNGLE, MID, BOT, SUPPORT).
* Invalid role compositions are not rejected; instead, DraftDeck displays diagnostic messages (e.g., missing roles, duplicate roles).

Examples:
* `draft 1 2 3 4 5` Drafts players at indices 1-5.
* `draft i/PlayerA i/PlayerB i/PlayerC i/PlayerD i/PlayerE` Drafts players by their IGNs.
* `draft 1 2 i/CarlK77 4 i/ElleM55` Mixes indices and IGNs.
* `draft 1 2 CarlK77 4 ElleM55` Alternative syntax with the `i/` prefix omitted for non-numeric IGNs.

Example output:
![Sample output for valid composition](images/draftSuccess.png)

#### Updating player statistics : `stats`

Updates the statistics of a player for a specific entity.

Format: `stats (INDEX | i/IGN) ent/ENTITY [k/KILLS] [d/DEATHS] [a/ASSISTS]`

* Updates the player at the specified `INDEX`, or with the specified IGN. The index refers to the index number shown beside each player in the list.
* These numbers are global: they are based on the full player list and stay the same after `find`/`filter`.
* The index **must be a positive integer** 1, 2, 3, …​
* `ent/ENTITY` is required and specifies which champion the statistics are for.
* At least one of the statistics fields (kills, deaths, assists) must be provided.
* Existing statistics for the entity will be added to the new values.
* Statistics for entities are added to the player's total statistics.

Examples:
* `stats 1 ent/Ahri k/50 d/10 a/20` Adds 50 kills, 10 deaths, and 20 assists to player 1's Ahri statistics.
* `stats 2 ent/Leona k/0 d/5 a/15` Adds 0 kills, 5 deaths, and 15 assists to player 2's Leona statistics.

#### Adding match results : `result`

Adds a match result to the match record.

Format: `result w/RESULT [date/yyyy-MM-dd] i/IGN ent/ENTITY s/KILLS-DEATHS-ASSISTS`

* All players and entities must exist in DraftDeck.
* Also updates the statistics of all players involved in the match.
* `w/RESULT` must be one of: `WIN`, `LOSE`, `DRAW`. Not case-sensitive.
* `date/yyyy-MM-dd` is optional. If not provided, uses the current date.
* There must be exactly 5 of `IGN`, `ENTITY`, `KILLS-DEATHS-ASSISTS`.
* The parameters can be in any order, the i-th occurrence of `ENTITY`, `KILLS-DEATHS-ASSISTS` will be
  mapped to the player with the i-th `IGN`.

Example:
* `result w/WIN i/PlayerA ent/Ahri s/10-2-8 i/PlayerB ent/Leona s/1-1-12 i/PlayerC ent/Evelynn s/5-6-15
i/PlayerD ent/Irelia s/2-19-4 i/PlayerE ent/Kayn s/6-3-8` Records a win today where
  * PlayerA played Ahri, killed 10 times, died 2 times and assisted 8 times.
  * PlayerB played Leona, killed 1 time, died 1 time and assisted 12 times.
  * PlayerC played Evelynn, killed 5 times, died 6 times and assisted 15 times.
  * PlayerD played Irelia, killed 2 times, died 19 times and assisted 4 times.
  * PlayerE played Kayn, killed 6 times, died 3 times and assisted 8 times.
* `result w/WIN i/PlayerA i/PlayerB i/PlayerC i/PlayerD i/PlayerE ent/Ahri ent/Leona ent/Evelynn ent/Irelia ent/Kayn
s/10-2-8 s/1-1-12 s/5-6-15 s/2-19-4 s/6-3-8` Records the exact same match as the above command.
* `result w/LOSE i/PlayerA ent/Ahri s/10-2-8 i/PlayerB ent/Leona s/1-1-12 i/PlayerC ent/Evelynn s/5-6-15
i/PlayerD ent/Irelia s/2-19-4 i/PlayerE ent/Kayn s/6-3-8 date/2025-12-31`
Records a loss that took place on 31st December 2025.


### Saving the data

DraftDeck data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

DraftDeck data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, DraftDeck will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the DraftDeck to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous DraftDeck home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE e/EMAIL i/IGN r/ROLE rank/RANK [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com i/JamesH88 r/BOT rank/PLATINUM I t/friend t/colleague`
**Clear** | `clear`
**Compare** | `compare INDEX1 INDEX2`<br> e.g., `compare 1 2`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Draft** | `draft (INDEX | i/IGN) (INDEX | i/IGN) (INDEX | i/IGN) (INDEX | i/IGN) (INDEX | i/IGN)`<br> e.g., `draft 1 2 i/CarlK77 4 i/ElleM55`
**Edit** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [i/IGN] [r/ROLE] [rank/RANK] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee r/JUNGLE rank/GOLD`
**Exit** | `exit`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Filter** | `filter [t/KEYWORD [MORE_KEYWORDS]...] [r/KEYWORD [MORE_KEYWORDS]...] [ent/KEYWORD [MORE_KEYWORDS]...]`<br> e.g., `filter t/pro r/bot ent/Jinx`
**Help** | `help`
**List** | `list`
**Result** | `result w/RESULT [date/yyyy-MM-dd] i/IGN ent/ENTITY s/KILLS-DEATHS-ASSISTS`<br> e.g., `result w/WIN i/AlexY42 ent/Ahri s/10-2-8 i/Bern_Storm ent/Leona s/1-1-12 i/Charlie99 ent/Evelynn s/5-6-15 i/DavidLi91 ent/Irelia s/2-19-4 i/IrfanZ ent/Kayn s/6-3-8`
**Stats** | `stats INDEX ent/ENTITY [k/KILLS] [d/DEATHS] [a/ASSISTS]`<br> e.g., `stats 1 ent/Ahri k/50 d/10 a/20`


### Glossary

* **IGN**: In-Game Name, a player's username in the game
* **Entity**: An umbrella term for a character played by the player in the game. In League of Legends, this refers to a 'Champion'. In other games, this may refer to an 'Agent', 'Operator', 'Hero', or whatever term that particular game uses.


# Walkthrough: Setting Up the National Esports Team

Welcome to DraftDeck! This walkthrough will guide you through a realistic scenario where you're a team manager setting up a new esports team for an upcoming tournament. You'll learn how to use DraftDeck's key features to manage players, analyze data, and prepare your team for competition.

## Scenario Overview
{:.no_toc}

You've just been hired as the team manager for the Singapore National Esports team, ahead of the upcoming Asian Games. Your first task is to:

1. Build a roster of 6 players (5 starters + 1 substitute)
2. Analyze player statistics and compare recruits
3. Draft a valid team composition
4. Record match data from practice games

Let's get started!

---

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you ever get stuck on a command, you can use the `help` command!
</div>

## Step 1: Launch DraftDeck and import the old roster.
{:.no_toc}

If this is your first time launching the app, then this is done for you already!

If not, simply close the app, delete the `data` folder, and relaunch it.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The rest of the screenshots in this tutorial will assume you have installed the release that comes with images. If you did not, not to worry - the images will simply be replaced with letters.
</div>

## Step 2: View Your Complete Roster
{:.no_toc}

Now let's see all your players at once.

### Command:
{:.no_toc}

```
list
```

**Expected Output:**
All 11 players are displayed in a numbered list, showing their name, phone, email, IGN, role, rank, and tags. You should see:

![List](images/WalkthroughList.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If the list is empty, or contains fewer than 11 players, you probably imported the tutorial data incorrectly. In such a scenario, simply delete the data folder and restart from Step 1.
</div>

---

## Step 3: Add new Players
{:.no_toc}

Between the last Asian Games and the current one, some new talent has emerged.
Specifically, a certain 'Koh Kai Jie' from team 'FaerieCharm'. His IGN is 'Dust', his rank is CHALLENGER, and he plays the top lane.
Suppose his phone number is 93032101, and his email is kkj@gmail.com. Then, the command to add him is as follows:

```
add n/Koh Kai Jie p/93032101 e/kkj@gmail.com i/Dust r/TOP rank/CHALLENGER t/FaerieCharm
```

**Expected Output:**
Scrolling to the bottom of the list, we see that he has been added to the list.

However, he has no champions played yet. This is expected - we have not added any stats for him!

![Add](images/WalkthroughAdd.png)

## Step 4: Adding past matches
{:.no_toc}

There are two ways we can go about adding past matches. We can either use the `result` command, which keeps track of dates, or the `stats` command.

For this case, we shall use the `stats` command. There are three games that we want to include:

| Champion | Kills | Deaths | Assists |
| - | - | - | - |
| Gwen | 2 | 9 | 4 |
| Rumble | 16 | 20 | 18 |
| Yorick | 3 | 6 | 5 |

The commands are thus as follows:

```
stats 12 ent/Gwen k/2 d/9 a/4
stats 12 ent/Rumble k/16 d/20 a/18
stats 12 ent/Yorick k/3 d/6 a/5
```

**Expected Output:**
Scrolling to the bottom of the list, we see that he now has three champions in his pool. Clicking on any one of them reveals the stats we have inputted.

![Stats](images/WalkthroughStats.png)



## Step 5: Find Specific Players
{:.no_toc}

Let's say you need to quickly find players with specific criteria.

### Find by Name
{:.no_toc}

Search for players named "Chen."

**Command:**
```
find Chen
```

**Expected Output:**
Lee Chen Ming and Chen Yi Hui are displayed.

![Find](images/WalkthroughFind.png)

### Filter by Role
{:.no_toc}

View all players tagged as "TOP."

**Command:**
```
filter r/top
```

**Expected Output:**
4 players are listed.
![Filter](images/WalkthroughFilter.png)

### Filter by Multiple Criteria
{:.no_toc}

Find all top players from team Impunity.

**Command:**
```
filter r/top t/Impunity
```

**Expected Output:**
Only 1 player is listed, CYH.
![FilterMultiple](images/WalkthroughFilterMultiple.png)

---

## Step 6: Compare Two Players
{:.no_toc}

You're considering whether to start Dust or Revive for an upcoming match. Let's compare them.

### Command:
{:.no_toc}

```
compare i/Dust i/Revive
```

**Expected Output:**
A side-by-side comparison showing:
- **Dust (Koh Kai Jie):** TOP, CHALLENGER
- **Revive (Daniel Tan):** TOP, CHALLENGER

This helps you make an informed decision on their different champion pools. What they have in common, and what champions they each uniquely play compared to each other.

In this case, the only common champion they play is Rumble.

![Compare](images/WalkthroughCompare.png)

---

## Step 7: Draft a Valid Team Composition
{:.no_toc}

Now let's practice drafting a valid 5-player team. A valid team needs exactly one player for each role.

### Command:
{:.no_toc}

```
draft 12 2 3 4 5
```

**Expected Output:**
A valid team composition message:
![DraftValid](images/WalkthroughDraftValid.png)

### Try an Invalid Composition
{:.no_toc}

Let's see what happens if we try to draft an invalid team (missing a role).

**Command:**
```
draft 12 2 3 4 6
```

**Expected Output:**
Validation output indicating the team composition is invalid, because you're missing a SUPPORT player (index 6 is Blaire, a TOP laner).
![DraftInvalid](images/WalkthroughDraftInvalid.png)

---

## Step 8: Record Match Result
{:.no_toc}

Your team just won their first practice match! Let's record the result. This is the statline for each player.

| Player | Champion | Kills | Deaths | Assists |
| - | - | - | - | - |
| Dust | Gwen | 3 | 0 | 4 |
| CraliX | Zed | 5 | 1 | 2 |
| Raven | Anivia | 1 | 6 | 5 |
| Ciela | Zeri | 2 | 4 | 0 |
| Kra | Ashe | 1 | 3 | 9 |

We could use the `stats` command again, but since we are tracking multiple players, combined with the fact that this is a match our team played together, we can use the `result` command to add them all at once, as well as storing the date and match result for future reference.

### Command:
{:.no_toc}

```
result w/WIN date/2026-09-01 i/Dust ent/Gwen s/3-0-4 i/CraliX ent/Zed s/5-1-2 i/Raven ent/Anivia s/1-6-5 i/Ciela ent/Zeri s/2-4-0 i/Kra ent/Ashe s/1-3-9
```

**Expected Output:**
A confirmation message indicating that the match has been saved.

```
Players: [Dust{statistics=Kills: 3, Deaths: 0, Assists: 4}, CraliX{statistics=Kills: 5, Deaths: 1, Assists: 2}, Raven{statistics=Kills: 1, Deaths: 6, Assists: 5}, Ciela{statistics=Kills: 2, Deaths: 4, Assists: 0}, Kra{statistics=Kills: 1, Deaths: 3, Assists: 9}]
```

## Step 9: Player Management
{:.no_toc}

### Editing Player Information
{:.no_toc}

Dust has a new phone number.

**Command:**
```
edit 12 p/98765432
```

**Expected Output:**
Confirmation that Dust's phone number has been updated.

### Removing a Player
{:.no_toc}

If you need to remove a player from your list:

**Command:**
```
delete 1
```

**Expected Output:**
Revive is removed from the list. The remaining players are renumbered accordingly.

---

## Summary
{:.no_toc}


Congratulations! You've successfully:

✓ Built a complete 5-player team roster
✓ Viewed and searched through your players
✓ Compared players to make roster decisions
✓ Drafted a valid team composition
✓ Recorded player statistics
✓ Documented match results

Your team is now set up and ready for the tournament! You can continue to use DraftDeck to:

- Add new players as your team grows
- Track player statistics over time
- Record all match results
- Analyze team performance with the `filter` and `compare` commands
- Draft different team compositions for different strategies

---

<div markdown="span" class="alert alert-primary">:bulb: **Pro Tip:**
All your data is automatically saved after every command. No need to manually save - your team roster and match records are safe!
</div>
