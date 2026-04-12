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

First, download the tutorial data file, found here.
Download and unzip it. It should contain a `data` folder. Use it to overwrite the existing the data folder.
Next, launch the application.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If there is no data folder that exists, that's fine! It just means you haven't launched the app before. You can just paste the folder in and it will still work correctly.
</div>

## Step 1.5: Install images (Optional)
{:.no_toc}

This step is optional, but recommended.
If you downloaded the release with the image pack, simply drop the `image` folder the same way you dropped the `data` folder, then restart the app. The final directory containing the app should look like this.


The rest of the screenshots in this tutorial will assume you have installed the `image` folder.

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

### Filter by Multiple Criteria
{:.no_toc}

Find all top players from team Impunity.

**Command:**
```
filter r/top t/Impunity
```

**Expected Output:**
Only 1 player is listed, CYH.

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
```
✓ Valid team composition!
TOP: Dust
JUNGLE: CraliX
MID: Raven
BOT: Ciela
SUPPORT: Kra
```

### Try an Invalid Composition
{:.no_toc}

Let's see what happens if we try to draft an invalid team (missing a role).

**Command:**
```
draft 12 2 3 4 6
```

**Expected Output:**
Validation output indicating the team composition is invalid, because you're missing a SUPPORT player (index 6 is Blaire, a TOP laner).

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
result w/WIN date/2026-09-01 i/Dust ent/Gwen k/3 d/0 a/4 i/CraliX ent/Zed k/5 d/1 a/2 i/Raven ent/Anivia k/1 d/6 a/5 i/Ciela ent/Zeri k/2 d/4 a/0 i/Kra ent/Ashe k/1 d/3 a/9
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
