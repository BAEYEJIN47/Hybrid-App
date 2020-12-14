<?php
   $con = mysqli_connect("localhost", "dit", "yejin0407!", "dit");
   $result = mysqli_query($con, "SELECT * FROM REPORT ORDER BY reportDate DESC;");
   $response = array();

   while($row = mysqli_fetch_array($result)){
      array_push($response, array("reportContent"=>$row[0], "reportName"=>$row[1], "reportDate"=>$row[2]));
   }
   
   echo json_encode(array("response"=>$response));
   mysqli_close($con);
?>