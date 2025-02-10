
import math
import random
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import os
import pathlib


BASE_DIR = pathlib.Path(__file__).resolve().parent
DISPLAY_RESULT = False
plt.style.use('seaborn-v0_8')


output_dir = os.path.join(BASE_DIR , "output", "python" )
# output_dir =  BASE_DIR + r"output/python"

# ALGORITHM
def hire_assistant(n , x ) :

    c = 0 # number of hires
    most_skilled_assistant = -1

    for i in range(1 , n + 1) :
        current_skill_assistant = random.randint(0 , int(x) )
        if current_skill_assistant > most_skilled_assistant :
            most_skilled_assistant = current_skill_assistant
            c += 1

    return c



def main(func , save_file_name) : 
    avg_metrics = np.array([])
    std_metrics = np.array([])

    for n in range(1 , 1001) :
        clist = np.array([])


        for _ in range(10) :
            
            # n = 1000
            x = math.log(n,2.0) # x = log2(n)


            clist = np.append(clist , hire_assistant(n ,x))
            
        avg_metrics = np.append(avg_metrics , np.average(clist))
        std_metrics = np.append(std_metrics , np.std(clist))

    if DISPLAY_RESULT : return 

    plt.plot( np.arange(1000) , std_metrics  , color = "blue" , label = "Std")
    plt.title("Standard Deviation")
    plt.savefig(f"{output_dir}/std_{save_file_name}.png")
    plt.show()

    plt.plot( np.arange(1000) , avg_metrics , color = "red" , label = "Average")
    plt.title("Average")
    plt.savefig(f"{output_dir}/mean_{save_file_name}.png")
    plt.show()

    x = np.linspace(0.1, 10, 400)
    y = np.log(x)
    plt.plot(x, y, label="ln(x)")


    plt.xlabel("x")
    plt.ylabel("ln(x)")
    plt.title("Plot of the Natural Logarithm Function")
    plt.legend()
    plt.savefig(f"{output_dir}/logn.png")
    plt.show()


func1 = lambda n : math.log(n,2.0)
func2 = lambda n : n
func3 = lambda n : n**3


if __name__ == "__main__" :


    main(func1 , "log2n") # x = log2(n)
    main(func2 , "n") # x = n
    main(func3 , "n3") # x = n ^ 3

    print(f"Executing complete ! Saved results in {output_dir}")



        
