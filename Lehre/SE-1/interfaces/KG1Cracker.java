import java.util.*;

public class KG1Cracker implements Cracker {
	private Secret secret;

	public void setSecret(Secret secret) {
		this.secret = secret;
	}

	public int[] crack() {
    	int notcontained = 0;
        //erster Schritt anzahl an Stellen herausfinden
        int ns[] = new int[0];
        int nextFree = 0;
        boolean correct[] = new boolean[1000];
        for(;;){
            try{
                secret.unlock(ns);
                break;
            } catch(TooFewDigitsException e){
                ns = new int[ns.length+1];
                for(int i = 0; i < ns.length; ++i)
                    ns[i] = 0;
                
                //Wichtig finde eine Kombination wo alle Ziffern falsch sind
                boolean cont = true;
                while(cont){
                	try{
                		secret.unlock(ns);
                	}
                	catch(WrongCombinationException a){
                		if(a.correctPlace() == 0 && a.correctDigit() == 0)
                			cont = false;
                		else{
                			for(int i = 0; i < ns.length; ++i)
                				++ns[i];
                		}
                		notcontained = ns[0];
                	}
                	catch(Exception a){
                		cont = false;
                	}
                }
                System.out.print("#");
            } catch(TooManyDigitsException e){
                System.out.println("Too many Strings");
            } catch (InvalidDigitException e){
                System.out.println("FEHLER: Zahl zu groß");
            } catch (WrongCombinationException e){
            	int digits = e.correctDigit();
            	int place = e.correctPlace();
            	int cntCorrect = cntCorrect(correct);
                if(digits-cntCorrect == 0){
                    System.out.print('+');
                    ++ns[nextFree];
                }else if(digits == place && digits - cntCorrect > 0){
                    //Neue Zahl liegt auf korrekter platz, nextFree weitersetzen
                    System.out.print('R');
                    correct[nextFree] = true;
                    nextFree = findFree(correct);
                    ns[nextFree] = 0;
                }else if(digits - cntCorrect >= 1 && place - cntCorrect == 0){
                    //korrekte Zahl aber auf falschem Platz
                    //shifte nicht korrekte um 1
                    System.out.print('>');
                    int tmp = ns[nextFree];
                    ns[nextFree] = notcontained;
                    int offs=nextFree;
                    for(int i = 1; i < ns.length; ++i){
                        if(!correct[(i+offs)%ns.length]){
                            nextFree=(i+offs)%ns.length;
                            break;
                        }
                    }
                    ns[nextFree] = tmp;
                }else{
                    System.out.println("Nicht gefangen");
                    System.out.println(e.correctDigit());
                    System.out.println(e.correctPlace());
                    System.out.println(cntCorrect(correct));
                }
            }
        }
        return ns;
    }

    private int cntCorrect(boolean[] arr)
    {
        int cnt = 0;
        for(boolean b: arr)
            cnt = b?cnt+1:cnt;
        return cnt;
    }
    private int findFree(boolean[] arr)
    {
        for(int i = 0; i < arr.length; ++i){ // nextFree wird verschoben
            if(!arr[i])
                return i;
        }
        return 999999;
    }
}