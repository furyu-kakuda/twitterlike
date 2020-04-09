# 環境
| 技術 | バージョン |
----|---- 
| Scala| 2.11.4 |
| Play Framework| 2.4.0 |
| MySQL | 8.0.19 |
| sbt | 0.13.8 |
| Node.js| 12.14.1 |
| Vue.js| 2.6.11 |
| TypeScript| 3.8.3 |

# ローカル起動方法
## MySQLのDocker Container起動

`docker run --volumes-from mysql_data --name mysql -e MYSQL_ROOT_PASSWORD=mysql -d -p 3306:3306 mysql`

## フロントBundle化+sbt起動
`./run.sh`
