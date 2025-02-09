# daa-g6-assignments

# Steps to run Python code

## 1. Create and activate Python virtual environment

```
python3 -m venv .venv
source .venv/bin/activate
```

## 2. Install python dependencies / modules 

```
pip3 install -r requirements.txt
```

## 3. Run the Jupyter NB

- Run hw1/p1.ipynb

- Select the ".venv" as virtual environment

The output plots of mean and standard deviation are saved in output folder.

# Steps to run Java code

## 1. Compile CouponCollector.java

```
javac CouponCollector.java
```

## 2. Run the CouponCollector program

```
java CouponCollector
```

A `results.csv` CSV file will be written to the project directory will the following columns:

n	| xA	| meanA	| stddevA	| xB | 	meanB	| stddevB	| xC	| meanC	| stddevC

where,

- n: The number of candidates
- xA: x = ln(n)
- meanA: Average value for xA
- stddevA: Standard Deviation for xA
- xB: x = n
- meanB: Average value for xB
- stddevB: Standard Deviation for xB
- xC: x = n^3
- meanC: Average value for xC
- stddevC: Standard Deviation for xC

The plots for average and standard deviation are plotted in the Jupyter notebook.
