## Step 1. Create the Loader and execute scripts. 
First create 2 text files for clients and yugabyte nodes named `clients.txt` and `yb_nodes.txt`.

## Step 2. Configure the client nodes.
This can be done as follows. Make sure that the environment has the ssh user
exported to the variable `SSH_USER` and the additional SSH AND SCP arguments
like the pem file or the port exported as `SSH_ARGS` and `SCP_ARGS`.
```sh
./setup_clients.sh
```

## Step 3. Create the TPCC tables.
This can be done from by:
```sh
g++ --std=c++11 run_tpcc_on_clients.cpp && ./a.out create
```

## Step 4. Load the data.
This can be done by:
```
g++ --std=c++11 run_tpcc_on_clients.cpp && ./a.out load
```

## Step 5. Enable the foreign keys.
This can be done as:
```sh
g++ --std=c++11 run_tpcc_on_clients.cpp && ./a.out enable-foreign-keys
```

## Step 6. Execute the program.
This can be done as:
```sh
g++ --std=c++11 run_tpcc_on_clients.cpp && ./a.out execute
```

If you do need to kill the currently running program at any stage, we can do
that as:
```sh
g++ --std=c++11 run_tpcc_on_clients.cpp && ./a.out kill
```
