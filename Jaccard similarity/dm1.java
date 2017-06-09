import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import java.util.*;

class dm1
{
public static void main(String args[])
{
	Random randomGenerator = new Random();
	System.out.println("hi");
	System.out.println("enter L");
	Scanner scanner = new Scanner(System.in);
	int l=scanner.nextInt();
	System.out.println("prime number"+l);
	int set[]=new int[l];
	for (int i=0;i<l;i++)
	{
		set[i]=i;
	}
	
	System.out.println("how many subsets?");
	int ns=scanner.nextInt();
	System.out.println("number of subsets"+ns);
	
	//byte mat[]={1,0,1};
	//int c=mat[0]&mat[2];
	//System.out.println("bitwise"+c);
	
	int subsets1[][]=new int[ns][l];
	for(int i=0;i<ns;i++)
	{
		int rand = randomGenerator.nextInt(l)+1;
		System.out.println("subset with elements"+rand);
		int subsets[]=comb(set,rand);
		Integer subsetss[]=new Integer[rand];
		
				/*	for(int j=0, k=0;j<ns && k<rand;j++)
					{
						if(j==subsets[k])
						{
							subsets1[m][j]=1;
							k++;
						}
						else
							subsets1[m][j]=0;
					}
					*/
					for(int q=0;q<rand;q++)
						subsetss[q]=subsets[q];
					for (int j=0; j<l;j++)
					{
						if (Arrays.asList(subsetss).contains(j) )
						subsets1[i][j]=1;
						
						else
							subsets1[i][j]=0;
					}
	}
	
	for(int i=0;i<ns;i++)
	{
		System.out.print("subset");
		for(int j=0;j<l;j++)
		{
			System.out.print(subsets1[i][j]);
		}
		System.out.println("");
	}
	
	System.out.println("choose 2 subsets");
	int ns1=scanner.nextInt();
	int ns2=scanner.nextInt();
	int ainb=0,aub=0;
	for(int i=0;i<l;i++)
	{
		if((subsets1[ns1-1][i]&subsets1[ns2-1][i])==1)
			ainb=ainb+1;
		if((subsets1[ns1-1][i]|subsets1[ns2-1][i])==1)
			aub=aub+1;
	}
	float njs=(float)ainb/aub;
	System.out. println("");
	System.out.println("normal jaccard similarity is "+njs);
	System.out.println("==============================================================");
	
	//calculating approximations
	System.out.println("how many hash functions?");
	int nhf=scanner.nextInt();
	System.out.println("num hash functions"+nhf);
	int havalues[][] = new int[nhf][l];
	//hi(x) = p(i)x + 2 mod L, where p(i) = ith prime
	for(int i=0;i<nhf;i++)
		for(int j=0;j<l;j++)
		{
			//havalues[i][j]=1;
			havalues[i][j]=(pfunc(i+1)*j)+(2%l);
			//System.out.println("prime numbers "+pfunc(i+1));
		}
	
	int sig[][]=new int[nhf][ns];
	System.out.println("==============================================================");
	System.out.println("	FINAL SIG");
	System.out.print("	");
	for(int i=0;i<ns;i++)
	System.out.print("set"+(i+1)+"	");
	System.out.println("");
	for(int i=0;i<nhf;i++)
		for(int j=0;j<ns;j++)
		sig[i][j]=9999999;
	
	for(int i=0;i<nhf;i++)
		for(int j=0;j<ns;j++)
		{
			for(int q=0;q<l;q++)
			{
				if(subsets1[j][q]==1 && sig[i][j]>havalues[i][q])
					sig[i][j]=havalues[i][q];
			}
		}
	
	for(int i=0;i<nhf;i++)
	{
		System.out.print("h"+i+"	");
		for(int j=0;j<ns;j++)
		{
			System.out.print(sig[i][j]+"	");
		}
		System.out.println("");
	}
	
	int haib=0,haub=0;
	Set<Integer> a = new HashSet<Integer>();
	Set<Integer> b = new HashSet<Integer>();
	for(int i=0;i<nhf;i++)
	{
		a.add(sig[i][ns1-1]);
		b.add(sig[i][ns2-1]);
		if(sig[i][ns1-1]==sig[i][ns2-1])
			haib++;
		if(sig[i][ns1-1]==sig[i][ns2-1])
			haub++;
		else
			haub=haub+2;
	}
	
	Set<Integer> union = a;
	union.addAll(b);
	
	a.retainAll(b);
	System.out.println("jaccard approximation between sets "+ns1+" and "+ns2+" is "+ (float)haib/haub);
	
	/*	
	for(int i=0;i<l;i++)
	{
		if(subsets1[0][i]==1 && sig[0][0]>havalues[0][i])
		{
			sig[0][0]=havalues[0][i];
		}
	}
	*/
	}
	
	
static int pfunc(int num)
{
	int pr=1;
	int prime=1;
	if(num==1)
		return prime;
	else
	{
		for(int i=2;;i++)
		{
			if(checkPrime(i))
			{
				pr++;
					if(pr==num)
					{
						prime=i;
						break;
					}
			}
		}
		return prime;
	}
}
	
static boolean checkPrime(int n) {
    if (n%2==0 && n!=2) return false;
    for(int i=3;i*i<=n;i+=2) {
        if(n%i==0)
            return false;
    }
    return true;
}
public static int[] comb(int[]  setele, int subele){
        int nele = setele.length;
		Random randomGenerator = new Random();
         
        if(subele > nele){
            System.out.println("not a valid input subset elements(subele)> number of elements in the set(setele)");
            System.exit(0);
        }

        int comb[] = new int[subele];

        int r = 0;      
        int ind = 0;
        int rand1 = randomGenerator.nextInt(nele)+1;
        while(r >= 0){
            if(ind <= (nele + (r - subele))){
                    comb[r] = ind;
                     
                if(r == subele-1){					
                    ind++;
					if(ind==rand1)
					break;
                }
                else{
                    ind = comb[r]+1;
                    r++;                                        
                }
            }
            else{
                r--;
                if(r > 0)
                    ind = comb[r]+1;
                else
                    ind = comb[0]+1;   
            }    
			
        }
		
		for (int i=0;i<subele;i++)
			System.out.print(comb[i]);
		System.out.println("");
			return comb;
    }
}