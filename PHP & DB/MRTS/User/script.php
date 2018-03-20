<?php

	if(isset($_GET["p_id"]))
	{
	
	 $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	$p_id=$query['p_id']; // Fetching URL Parameter
	$report_type=$query['report_type']; // Fetching URL Parameter
	$date=$query['date']; // Fetching URL Parameter
	
		$files = array();
		
		foreach (glob("$p_id/$report_type/$date/*.jpg") as $file)
		{
			array_push($files, basename($file));
		}
		
		header('Content-type: application/json');
		
		echo json_encode($files);
	}		
?>