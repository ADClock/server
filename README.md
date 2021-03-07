# ADClock server
This project is the master for a ClockWall. It allows you to play with the clock wall as you want.

## Features
TODO (nothing by now)

## Requirements
Currently this project is build for an raspberry pi. See [raspberry-runner](https://github.com/ADClock/raspberry-runner) for a full setup guide.

Our goal is it, to make this code runnable on every maschine - even without an connected ADClock. So you can configure animations and push them later to your clock.


## ADClock command set
The specification is written in EBNF notation following [ISO 14977](http://www.cl.cam.ac.uk/~mgk25/iso-14977.pdf).

```
command         = (clockcommand | scenecommand);
handselection   = clockselection, [ "H" ], [ "M" ], { ",", handselection };
clockselection  = ( "." | clock | clock, "-", clock );
clock           = 1..24
```

A clockcommand is used to mutate one or multiple hands. When either `H` or `M` is given in `handselection` then both hands of given `clockselection` are selected. 

```
clockcommand   = (POS | SPD | DIR | DLY | COL | CAL | RST);
POS            = "SET", handselection, (1..1714);
SPD            = "SPD", handselection, (1..5);
DIR            = "DIR", handselection, ("F" | "B");
DLY            = "DLY", handselection, (1..);
COL            = "COL", handselection, (1..1714), ("F" | "B");
CAL            = "CAL", clockselection;
RST            = "RST", handselection;
```

Along with these commands you can specifiy scene commands:
```
scenecommand   = (RUN | SLP | REF | FUN);
RUN            = "RUN";
SLP            = "SLP", 1..;
REF            = "REF", <name-of-other-script>;
FUN            = "FUN", <name-of-complex-function>;
```
