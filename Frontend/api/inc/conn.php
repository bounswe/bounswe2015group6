<?php

class Db {
    // The database connection
    protected static $connection;

    /**
     * Connect to the database
     * 
     * @return bool false on failure / mysqli MySQLi object instance on success
     */
    public function connect() {    
        // Try and connect to the database
        if(!isset(self::$connection)) {
            // Load configuration as an array. Use the actual location of your configuration file
            $config = parse_ini_file('config.ini'); 
            self::$connection = new mysqli($config['host'], $config['username'], $config['password'], $config['dbname']);
            mysqli_set_charset(self::$connection, "utf8");
        }

        // If connection was not successful, handle the error
        if(self::$connection === false) {
            // Handle error - notify administrator, log to a file, show an error screen, etc.
            return false;
        }
        return self::$connection;
    }

    /**
     * Query the database
     *
     * @param $query The query string
     * @return mixed The result of the mysqli::query() function
     */
    public function query($query) {
        // Connect to the database
        $connection = $this -> connect();

        // Query the database
        $result = $connection -> query($query);

        return $result;
    }

    /**
     * Multiple Query the database
     *
     * @param $query The query string
     * @return mixed The result of the mysqli::multi_query() function
     */
    public function multi_query($query) {
        // Connect to the database
        $connection = $this -> connect();

        // Query the database
        $result = $connection -> multi_query($query);

        return $result;
    }

    /**
     * Fetch row from the database (SELECT query)
     *
     * @param $query The query string
     * @return bool False on failure / array Database row on success
     */
    public function select($query) {
        $result = $this -> query($query);
        if($result === false) {
            return false;
        }
        if($result->num_rows > 0){
        	return $result -> fetch_assoc();
        }
        return false;
    }


    /**
     * Fetch rows from the database (SELECT query)
     *
     * @param $query The query string
     * @return bool False on failure / array Database rows on success
     */
    public function select_all($query) {
        $rows = array();
        $result = $this -> query($query);
        if($result === false) {
            return false;
        }
        if($result->num_rows > 0){
            while ($row = $result -> fetch_assoc()) {
                $rows[] = $row;
            }
            return $rows;
        }
        return false;
    }

    /**
     * Fetch the last error from the database
     * 
     * @return string Database error message
     */
    public function error() {
        $connection = $this -> connect();
        return $connection -> error;
    }

    /**
     * Quote and escape value for use in a database query
     *
     * @param string $value The value to be quoted and escaped
     * @return string The quoted and escaped string
     */
    public function quote($value) {
        $connection = $this -> connect();
        return "'" . $connection -> real_escape_string($value) . "'";
    }

    public function get_insert_id() {
    	$connection = $this -> connect();
    	return $connection -> insert_id;
    }

}
?>