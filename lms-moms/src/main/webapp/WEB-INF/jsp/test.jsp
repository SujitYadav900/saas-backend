<!doctype html>
<html lang="en">
<head>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <style>
      body{
          font-family: 'Raleway', sans-serif;
          background: #E9ECE9;
      }
      .comments-main{
          background: #FFF;
      }
      .comment time, .comment:hover time,.icon-rocknroll, .like-count {
          -webkit-transition: .25s opacity linear;
          transition: .25s opacity linear;
      }
      .comments-main ul li{
          list-style: none;
      }
      .comments .comment {
          padding: 5px 10px;
          background: #00AF90;
      }
      .comments .comment:hover time{
          opacity: 1;
      }
      .comments .user-img img {
          width: 50px;
          height: 50px;
      }
      .comments .comment h4 {
          display: inline-block;
          font-size: 16px;
      }
      .comments .comment h4 a {
          color: #404040;
          text-decoration: none;
      }
      .comments .comment .icon-rocknroll {
          color: #545454;
          font-size: .85rem;
      }
      .comments .comment .icon-rocknroll:hover {
          opacity: .5;
      }
      .comments .comment time,.comments .comment .like-count,.comments .comment .icon-rocknroll {
          font-size: .75rem;
          opacity: 0;
      }
      .comments .comment time, .comments .comment .like-count {
          font-weight: 300;
      }
      .comments .comment p {
          font-size: 13px;
      }
      .comments .comment p .reply {
          color: #BFBFA8;
          cursor: pointer;
      }
      .comments .comment .active {
          opacity: 1;
      }
      .icon-rocknroll {
          background: none;
          outline: none;
          cursor: pointer;
          margin: 0 .125rem 0 0;
      }
      .comments .comment:hover .icon-rocknroll,.comments .comment:hover .like-count {
          opacity: 1;
      }
      .comment-box-main{
          background: #CA1D5F;
      }
      @media (min-width: 320px) and (max-width: 480px){
          .comments .comment h4 {
              font-size: 12px;
          }
          .comments .comment p{
              font-size: 11px;
          }
          .comment-box-main .send-btn button{
              margin-left: 5px;
          }
      }
      .chatoption{
          background-color: white;
          border-radius: 6px;
          text-align: center;
          cursor: pointer;
          box-shadow: 0 0 black;
          font-size: 12px;

      }
      .chatoption:hover{
          box-shadow: 1px 1px 1px 1px gray;
      }
  </style>
</head>
<body>
<div class="container">
     <div class="row mt-5">
        <div class="col-md-6 offset-md-3 col-sm-6 offset-sm-3 col-12 comments-main pt-4 rounded">
            <ul class="p-0">
                <li>
                    <div class="row comments mb-2">
                        <div class="col-md-2 col-sm-2 col-3 text-center user-img">
                            <img id="profile-photo" src="http://nicesnippets.com/demo/man01.png" class="rounded-circle" />
                        </div>
                        <div class="col-md-9 col-sm-9 col-9 comment rounded mb-2">
                            <h4 class="m-0"><a href="#">Jacks David</a></h4>
                            <time class="text-white ml-3">1 hours ago</time>
                            <like></like>
                            <p class="mb-0 text-white">Thank you for visiting all the way from New York.</p>
                        </div>
                    </div>
                </li>
                <ul class="p-0">
                    <li>
                        <div class="row comments mb-2">
                            <div class="col-md-2 offset-md-2 col-sm-2 offset-sm-2 col-3 offset-1 text-center user-img">
                                <img id="profile-photo" src="http://nicesnippets.com/demo/man02.png" class="rounded-circle" />
                            </div>
                            <div class="col-md-7 col-sm-7 col-8 comment rounded mb-2">
                                <h4 class="m-0"><a href="#">Steve Alex</a></h4>
                                <time class="text-white ml-3">1 week ago</time>
                                <like></like>
                                <p class="mb-0 text-white">Thank you for visiting all the way from NYC.</p>
                            </div>
                        </div>
                    </li>
                </ul>
            </ul>
            <ul class="p-0">
                <li>
                    <div class="row comments mb-2">
                        <div class="col-md-2 col-sm-2 col-3 text-center user-img">
                            <img id="profile-photo" src="http://nicesnippets.com/demo/man03.png" class="rounded-circle" />
                        </div>
                        <div class="col-md-9 col-sm-9 col-9 comment rounded mb-2">
                            <h4 class="m-0"><a href="#">Andrew Filander</a></h4>
                            <time class="text-white ml-3">7 day ago</time>
                            <like></like>
                            <p class="mb-0 text-white">Thank you for visiting all the way from New York.</p>
                        </div>
                    </div>
                </li>
                <ul class="p-0">
                    <li>
                        <div class="row comments mb-2">
                            <div class="col-md-2 offset-md-2 col-sm-2 offset-sm-2 col-3 offset-1 text-center user-img">
                                <img id="profile-photo" src="http://nicesnippets.com/demo/man04.png" class="rounded-circle" />
                            </div>
                            <div class="col-md-7 col-sm-7 col-8 comment rounded mb-2">
                                <h4 class="m-0"><a href="#">james Cordon</a></h4>
                                <time class="text-white ml-3">1 min ago</time>
                                <like></like>
                                <p class="mb-0 text-white">Thank you for visiting from an unknown location.</p>
                            </div>
                        </div>
                    </li>
                </ul>
            </ul>
            <ul class="p-0">
                <li>
                    <div class="row comments mb-2">
                        <div class="col-md-2 col-sm-2 col-3 text-center user-img">
                            <img id="profile-photo" src="http://nicesnippets.com/demo/man01.png" class="rounded-circle" />
                        </div>
                        <div class="col-md-9 col-sm-9 col-9 comment rounded mb-2">
                            <h4 class="m-0"><a href="#">Tye Simmon</a></h4>
                            <time class="text-white ml-3">1 month ago</time>
                            <like></like>
                            <p class="mb-0 text-white">Thank you for visiting all the way from New York.</p>
                        </div>
                    </div>
                </li>
            </ul>
            <div class="row comment-box-main p-3 rounded-bottom">
                <div class="col-md-9 col-sm-9 col-9 pr-0 comment-box">
                    <input type="text" class="form-control" placeholder="comment ...." />
                </div>
                <div class="col-md-3 col-sm-2 col-2 pl-0 text-center send-btn">
                    <button class="btn btn-info">Send</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/assets/plugins/jQuery/jquery-1.12.4.min.js" type="text/javascript"></script>
<script>
    var map={};
    var check={};
    var checkinner={};
    map["questionId"]="asdasd";
    map["index"]=1;
    map["question"]="What is your name?"
    map["qusType"]=1;
    map["questionTypes"]={};
    check["lengthCheck"]=true;
    checkinner["min"]=10;
    checkinner["max"]=10;
    check["lengthCheckValidation"]=checkinner;
    map["checks"]=check;

    function loadQuestion(id) {
        var questionmap=map;
        var question=map["question"];
        $('#ullistbody').append("<li>"+question+"</li>")
        loadOptionByType(questionmap)

    }
    function loadOptionByType(questionmap) {
        console.log(questionmap)
        var type=questionmap["qusType"];
        var option=questionmap["questionTypes"];
        switch (type) {
            case 1:
                $('#chatanswertype').html("<form><input type='text'></form>")
                console.log("Input Type text");
                break;
            case 2:
                $('#chatanswertype').html("<form><input type='file'></form>")
                console.log("Input Type File");
                break;
            case 3:
                $('#chatanswertype').html("<form><input type='number'></form>")
                console.log("Input Type Number");
                break;
            case 4:
                $('#chatanswertype').html("<form><input type='url'></form>")
                console.log("Input Type Url");
                break;
            case 5:
                $('#chatanswertype').html("<input type='date'>")
                console.log("Input Type Date");
                break;
            case 6:
                $('#chatanswertype').html("<input type='datetime-local'>")
                console.log("Input Type Date Time");
                break;
            case 7:
                console.log("Mcq");
                break;
            case 8:
                console.log("No Replies");
                nextQuestionPlease(questionmap["index"],questionmap["questionId"])
                break;

        }
    }
    function loadOption(json) {
        var html="";
        for(var i=0;i<json.length;i++)
        {
            switch (json[i].) {

            }
            html=html+"<li></li>";
        }
    }

    function nextQuestionPlease(index,questionId) {
        console.log("Loading New Question Skiping")
    }

    loadQuestion("asdasd")


</script>




</html>