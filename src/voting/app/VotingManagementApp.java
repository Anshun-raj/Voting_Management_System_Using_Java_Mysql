package voting.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class VotingManagementApp 
{
	static int num;
	String can1;
	String can2;
	String can3;
	
	String[] arr=new String[num];
	Scanner sc=new Scanner(System.in);
	public static void main(String arg[])throws SQLException
	{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter total no. of voters who can vote:-");
		num=sc.nextInt();		
		VotingManagementApp obj=new VotingManagementApp();
		obj.addCan();
		obj.addVote();
		obj.addWinner();
	}
	
		public void addCan()
		{
			System.out.println("Enter the name of first candidate:-");
			can1=sc.next();
			System.out.println("Enter the name of second candidate:-");
			can2=sc.next();
			System.out.println("Enter the name of third candidate:-");
			can3=sc.next();
			System.out.println("These are the candidates of the upcoming election:-"+can1+" "+can2+" "+can3);
		}
		public void addVote()throws SQLException
		{
			
			
			for(int i=0;i<num;i++)
			{
				System.out.println("Enter the vote of voters:-");
				Scanner sc=new Scanner(System.in);
				arr[i]=sc.next();
				
				String s1="INSERT INTO voter" +
				        "  (voter_nm) VALUES " +
				        " (?);";
		        try (Connection con = DriverManager
		            .getConnection("jdbc:mysql://localhost:3306/test","root","");
		        		
		        		PreparedStatement prsmt = con.prepareStatement(s1)) 
		            {
		                
		              prsmt.setString(1, arr[i]);
		              
		            }
		        catch (SQLException e) {
		          System.out.println(e);
		        }
			}
			for(int j=0;j<num;j++)
			{
				System.out.println("The vote given by the voters to the candidates are:-"+arr[j]);
			}
			
		}
		public void addWinner()
		{
			int c1 = 0;
			int c2 = 0;
			int c3 = 0;
			for(int j=0;j<num;j++)
			{
				if(arr[j].equals(can1))
				{
					c1+=1;
				}
				else if(arr[j].equals(can2))
				{
					c2+=1;
				}
				else if(arr[j].equals(can3))
				{
					c3+=1;
				}
				else 
				{
					System.out.println("Candidate is not listed");
				}
			}
			if((c1>c2) && (c1>c3))
			{
				System.out.println("Winner is:-"+can1+" "+"no. of vote is:-"+c1);
			}
			else if((c2>c1) && (c2>c3))
			{
				System.out.println("Winner is:-"+can2+" "+"+no. of vote is:-"+c2);
			}
			else if((c3>c1) && (c3>c2))
			{
				System.out.println("Winner is:-"+can3+" "+"no. of vote is:-"+c3);
			}
			else {
				System.out.println("No one won the election");
			}
		}

}

