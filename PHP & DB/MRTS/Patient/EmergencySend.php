<?php

$Host="localhost";
$DBUsername="root";
$DBPassword="down";
$DB="mrtnfc";
$Con = mysqli_connect($Host, $DBUsername, $DBPassword,$DB);

if (!$Con)
{
    die("Connection failed: " . mysqli_connect_error());
}
else
{
    if(isset($_GET['Username']))
    {
        $Username=$_GET['Username'];
      //  $Password=$_GET['Password'];
        $sql="SELECT * FROM `users` WHERE `UserName`='$Username'";
    }
    elseif(isset($_GET['NFCID']))
    {
        $ID=$_GET['NFCID'];
      //  $Password=$_GET['Password'];
	  // $ID="048E2312FF3880";
	   //$Password="ajit";
        $sql="SELECT * FROM `users` WHERE NfcID='$ID'";
    }
    else
    {
        die("Error : No Post");
    }
    $result=$Con->query($sql);
    $row = $result->fetch_assoc();
    if($result->num_rows==0)
    {
        echo "fail ";
    }
    else
    {
        $result2 = array();
        array_push($result2,
        array(
           // 'FirstName'=>$row['FirstName'],
            //'MiddleName'=>$row['MiddleName'],
           // 'LastName'=>$row['LastName'],
           // 'Email'=>$row['Email'],
           // 'State'=>$row['State'],
			//'City'=>$row['City'],
			'ID'=>$row['ID'],
          //  'Type'=>$row['Type']
			
        ));
        echo json_encode(array("result"=>$result2));
		
    }
}
?>

