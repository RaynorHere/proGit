import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Scanner;

public class BruteLoops {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		// Okay. So. First order of business: we find out what salt we're working with.
		// To that end, we have several test passwords to work with, which I think will
		// help us figure it out.

//		String adminHash = "b86a4d73ea36be60f4f416644e0b1386c40043819d75cd037ddfb9c109a18c53";
//		String guestHash = "82102dd00e33fd347153acdef96969fa719b2ede2d0efe170cf154efcec2bea4";
//		String passwordHash = "670fa3af18302f4e1f0f25320cb830232b38712e5a5d20f07eb72459ddb5cf44";
//		String qrstuvwxHash = "e5b3434ba7ae17ca38b3a0f612e6bd8002cab2f81fcd9a08019e20c7c0ae9560";
//		String eaceiyrxHash = "5a0ef866d37aa89e6d07dc39756b3fd4f0750b8a4c04a369020fec37eb690a51";
//		String anyHash = "d32ae1dc7c7485d40e68b5c5b39f1c8640c780c1cff1c0c0ad0f51c7c5277847";
//		String wildcardHash = "4225d01c8f3cde45ef062222f89be1b6399cbb7426f5c84462ae5d806ea0d8f4";
//		String zzyzxHash = "b026b32891b6995e258865e7c885e4a03d634143361e555451c0f2fd70465627";
//		String hgfedcbaHash = "152efbecd0bb132f79443058818e31e5d65ba35d28386238947645482e0e2249";
//		String tryagainHash = "fb215cefa5d7b4496459bcef894feb119d5551f1eff021db584152bfaadba161";

		/*
		 * Now. Obviously, if I've understood this assignment correctly, everything uses
		 * the same hash. So, we're going to put the top password, admin, through a
		 * small battery of tests to see if we can match its hash, which would indicate
		 * we have found the salt. We will test that theory by putting that salt
		 * appended on to every other password and make sure that those hashes come out
		 * the same. If they do, we can begin our brute forcer. So!
		 */

		// THE GREAT QUEST TO FIND THE SALT

//		String guineaPig = "admin";

		/*
		 * Characters a-z, and numbers 0-9 play pretty nicely; those are UNICODE decimal
		 * values 97-122 and 48-57, respectively. The special chars, on the other hand,
		 * are out of sequence. The character math for this is going to get messy.
		 */

		// ! being the UNICODE character with the lowest decimal value...
//		String singSalt = "!";
//		String doubSalt = "!!";
//		String tripSalt = "!!!";
//		String quadSalt = "!!!!";
//
//		// This will be our combination of the salts and password guess
//		String calcGuess = "";
//
//		System.out.println((guineaPig));
//		System.out.println("Regular SHA256 of the above:");
//		System.out.println(hashedHex(SHAzam(guineaPig)) + "\n");

//		// Single Salt Possibilities, Head
//		
//		int oneSpot = singSalt.charAt(0);
//		
//		while (oneSpot <= 'z') {
//			String oneSalt = String.valueOf((char)oneSpot);
//			oneSpot++;
//			calcGuess = oneSalt + guineaPig;
//			if (hashedHex(SHAzam(calcGuess)).equals(adminHash)) {
//				System.out.println(calcGuess);
//			}
//			// Test
//			System.out.println(calcGuess);
//		}
//
//		
//
//		// Double Salt Possibilities, Head
//
//		int tenSpot = doubSalt.charAt(0);
//		oneSpot = doubSalt.charAt(1);
//
//		// This is where it gets tricky, but I might have an idea to solve it
//		// If I can do nested loops to do every permutation of two-digit salts,
//		// I should be able to do it for up to four
//
//		while (tenSpot <= 'z') {
//
//			while (oneSpot <= 'z') {
//				String tenSalt = String.valueOf((char) tenSpot + String.valueOf((char) oneSpot));
//				oneSpot++;
//				calcGuess = tenSalt + guineaPig;
//				if (hashedHex(SHAzam(calcGuess)).equals(adminHash)) {
//					System.out.println(calcGuess);
//				}
				 // Test
//				System.out.println(calcGuess);
//			}
//			oneSpot = 0;
//			tenSpot++;
//
//			/*
//			 * Something interesting happened while I was setting these loops up, and I sort
//			 * of stumbled into what they are right now, which does, in fact, run every
//			 * combination from "00admin" to "zzadmin" and everything in between. And I DO
//			 * mean EVERYTHING. That is, a bunch of values that the salt could not possibly
//			 * be (like "AAadmin" or "'>admin"). Now, clearly, this is wasted CPU, and I
//			 * know that. However, I have a shocking amount of stuff to do during spring
//			 * break, which is where I have time to actually build this, and in the interest
//			 * of expediency, I'm going to leave these inefficiencies in, because I think it
//			 * will actually end up saving me time on the back end, rather than writing in
//			 * all the catches and special circumstances
//			 */
//		}
//		
//		
//		// Triple Salt Possibilities, Head
//		
//		int hundSpot = tripSalt.charAt(0);
//		tenSpot = tripSalt.charAt(1);
//		oneSpot = tripSalt.charAt(2);
//		
//		while (hundSpot <= 'z') {
//			
//			while (tenSpot <= 'z') {
//				
//				/* I think above taught me I only need to have one moment of salting and hashing
//				* The other values will increase after this loop completes, and when this loop
//				* repeats, those new values are taken into account
//				*/  
//				while (oneSpot <= 'z') {
//					String hundSalt = String.valueOf((char)hundSpot + String.valueOf((char)tenSpot + String.valueOf((char)oneSpot)));
//					oneSpot++;
//					calcGuess = hundSalt+guineaPig;
//					if (hashedHex(SHAzam(calcGuess)).equals(adminHash)) {
//						System.out.println(calcGuess);
//					}
//					// Test
////					System.out.println(calcGuess);
//				}
//				oneSpot = 0;
//				tenSpot++;				
//			}
//			tenSpot = 0;
//			hundSpot++;			
//		}
//		/* Yep, proven that works, but it takes my top-of-the-line computer almost a minute
//		* to open the text file I am not dumping the output to because the console does not
//		* support scrolling back far enough to ensure I'm starting at "000admin" before ending
//		* at "zzzadmin". Which is EXTREMELY funny to me. Checking the .txt, though, that is
//		* EXACTLY what's happening. So we're getting there.
//		*/
//		
//		
//		// Quadruple Salt Possibilities, Head
//		
//		int thouSpot = quadSalt.charAt(0);
//		hundSpot = quadSalt.charAt(1);
//		tenSpot = quadSalt.charAt(2);
//		oneSpot = quadSalt.charAt(3);
//		
//		// Oh my God, this is going to be such a mess and yet I'm laughing
//		
//		while (thouSpot <= 'z') {
//			
//			while (hundSpot <= 'z') {
//				
//				while (tenSpot <= 'z') {
//					
//					while (oneSpot <= 'z') {
//						String thouSalt = String.valueOf((char)thouSpot + String.valueOf((char)hundSpot + 
//								String.valueOf((char)tenSpot + String.valueOf((char)oneSpot))));
//						oneSpot++;
//						calcGuess = thouSalt+guineaPig;
//						if (hashedHex(SHAzam(calcGuess)).equals(adminHash)) {
//							System.out.println(calcGuess);
//						}
//						// Test
////						System.out.println(calcGuess);
//					}
//					oneSpot = 0;
//					tenSpot++;
//				}
//				tenSpot = 0;
//				hundSpot++;
//			}
//			hundSpot = 0;
//			thouSpot++;
//		}
//		/* This must be very, very, very funny to see; I KNOW I'm doing this as CLUMSILY and INEFFICIENTLY
//		* as possible, but I've never built a brute forcer before; Hell, I've never done sequential
//		* CHARACTER MATH BEFORE, so this is just tons of fun, even if it looks like it was done by a 
//		* drunk child
//		*/
//		
//		// Okay. So.  Now we do the same exact process, just gotta flip heads to tails.
//		
//		
//		// Single Salt Possibilities, Tail
//		
//		oneSpot = singSalt.charAt(0);
//		
//		while (oneSpot <= 'z') {
//			String oneSalt = String.valueOf((char)oneSpot);
//			oneSpot++;
//			calcGuess = guineaPig + oneSalt;
//			if (hashedHex(SHAzam(calcGuess)).equals(adminHash)) {
//				System.out.println(calcGuess);
//			}
//			// Test
////			System.out.println(calcGuess);
//		}
//
//		
//
//		// Double Salt Possibilities, Head
//
//		tenSpot = doubSalt.charAt(0);
//		oneSpot = doubSalt.charAt(1);
//
//		// This is where it gets tricky, but I might have an idea to solve it
//		// If I can do nested loops to do every permutation of two-digit salts,
//		// I should be able to do it for up to four
//
//		while (tenSpot <= 'z') {
//
//			while (oneSpot <= 'z') {
//				String tenSalt = String.valueOf((char) tenSpot + String.valueOf((char) oneSpot));
//				oneSpot++;
//				calcGuess = guineaPig + tenSalt;
//				if (hashedHex(SHAzam(calcGuess)).equals(adminHash)) {
//					System.out.println(calcGuess);
//				}
//				// Test
////				System.out.println(calcGuess);
//			}
//			oneSpot = 0;
//			tenSpot++;
//		}
//		
//		
//		// Triple Salt Possibilities, Head
//		
//		hundSpot = tripSalt.charAt(0);
//		tenSpot = tripSalt.charAt(1);
//		oneSpot = tripSalt.charAt(2);
//		
//		while (hundSpot <= 'z') {
//			
//			while (tenSpot <= 'z') {
//				
//				while (oneSpot <= 'z') {
//					String hundSalt = String.valueOf((char)hundSpot + String.valueOf((char)tenSpot + String.valueOf((char)oneSpot)));
//					oneSpot++;
//					calcGuess = guineaPig + hundSalt;
//					if (hashedHex(SHAzam(calcGuess)).equals(adminHash)) {
//						System.out.println(calcGuess);
//					}
//					// Test
////					System.out.println(calcGuess);
//				}
//				oneSpot = 0;
//				tenSpot++;				
//			}
//			tenSpot = 0;
//			hundSpot++;			
//		}
//
//		
//		// Quadruple Salt Possibilities, Head
//		
//		thouSpot = quadSalt.charAt(0);
//		hundSpot = quadSalt.charAt(1);
//		tenSpot = quadSalt.charAt(2);
//		oneSpot = quadSalt.charAt(3);
//		
//		while (thouSpot <= 'z') {
//			
//			while (hundSpot <= 'z') {
//				
//				while (tenSpot <= 'z') {
//					
//					while (oneSpot <= 'z') {
//						String thouSalt = String.valueOf((char)thouSpot + String.valueOf((char)hundSpot + 
//								String.valueOf((char)tenSpot + String.valueOf((char)oneSpot))));
//						oneSpot++;
//						calcGuess = guineaPig + thouSalt;
//						if (hashedHex(SHAzam(calcGuess)).equals(adminHash)) {
//							System.out.println(calcGuess);
//						}
//						// Test
////						System.out.println(calcGuess);
//					}
//					oneSpot = 0;
//					tenSpot++;
//				}
//				tenSpot = 0;
//				hundSpot++;
//			}
//			hundSpot = 0;
//			thouSpot++;
//		}

		/*
		 * Mercifully, the salt on admin appeared to be "$@1t" (which I'm now laughing
		 * at, seeing it), and it was salted on the head. So if that's true, we'll
		 * comment out everything above and run it through all our other given test
		 * passwords to make sure they come out matching. Total time of crunching before
		 * finding the salt came out to just over 15 seconds
		 */

		String foundSalt = "$@1t";

		// START THE REACTOR AND TEST THE VALUES

//		String test1 = foundSalt+"admin";
//		
//		if (hashedHex(SHAzam(test1)).equals(adminHash)) {
//			System.out.println("Yep!");
//		}
//		else
//			System.out.println("Nope!");
//		
//		String test2 = foundSalt+"guest";
//		
//		if (hashedHex(SHAzam(test2)).equals(guestHash)) {
//			System.out.println("Yep!");
//		}
//		else
//			System.out.println("Nope!");
//
//		String test3 = foundSalt+"password";
//		
//		if (hashedHex(SHAzam(test3)).equals(passwordHash)) {
//			System.out.println("Yep!");
//		}
//		else
//			System.out.println("Nope!");
//		
//		String test4 = foundSalt+"qrstuvwx";
//		
//		if (hashedHex(SHAzam(test4)).equals(qrstuvwxHash)) {
//			System.out.println("Yep!");
//		}
//		else
//			System.out.println("Nope!");
//		
//		String test5 = foundSalt+"eaceiyrx";
//		
//		if (hashedHex(SHAzam(test5)).equals(eaceiyrxHash)) {
//			System.out.println("Yep!");
//		}
//		else
//			System.out.println("Nope!");
//		
//		String test6 = foundSalt+"any";
//		
//		if (hashedHex(SHAzam(test6)).equals(anyHash)) {
//			System.out.println("Yep!");
//		}
//		else
//			System.out.println("Nope!");
//		
//		String test7 = foundSalt+"wildcard";
//		
//		if (hashedHex(SHAzam(test7)).equals(wildcardHash)) {
//			System.out.println("Yep!");
//		}
//		else
//			System.out.println("Nope!");
//		
//		String test8 = foundSalt+"zzyzx";
//		
//		if (hashedHex(SHAzam(test8)).equals(zzyzxHash)) {
//			System.out.println("Yep!");
//		}
//		else
//			System.out.println("Nope!");
//		
//		String test9 = foundSalt+"hgfedcba";
//		
//		if (hashedHex(SHAzam(test9)).equals(hgfedcbaHash)) {
//			System.out.println("Yep!");
//		}
//		else
//			System.out.println("Nope!");
//		
//		String test10 = foundSalt+"tryagain";
//		
//		if (hashedHex(SHAzam(test10)).equals(tryagainHash)) {
//			System.out.println("Yep!");
//		}
//		else
//			System.out.println("Nope!");

		// All right. We have proof that the salt is always $@1t, and it's always on the
		// head
		
		// Target values
		String pass1Hash = "11a5167b8c1b29c78e6253f44631df9cb478ba4d6363fcd7316daefcbeccad52";
		// hello

		String pass2Hash = "aada9d0f7c7f789c8e5c17cdcbbbabbd71bc34855c071ff8dc3318a36db44afb";
		// monkey

		String pass3Hash = "1e12a60583506169310c185eb3f8fb0dbccce738e2035be084b11441f9876c22";
		// google

		String pass4Hash = "ff4a7dc27bc2deff2d01590365e4391019021be9bdf20246a4992ce69ac17494";
		// zecg

		String pass5Hash = "f69ff18a4221aa32ca472e18a0783c9ff19c3f380f06f18c97a3c1f326886c3f";
		// kccohm

		String pass6Hash = "376f97c7a52b6f63001146a11ece0629a28f6f96fe046acf715f9edbc6ae0eb2";
		// ozkrd

		String pass7Hash = "d45ab4e6f67e3a4fbf6ea589feb5566e2104c0df80ece9e22200cad79a7d18fb";
		// lcrbvg

		String pass8Hash = "ca4465942f197198f2a88569d65eadbe81f64b11078b7cef5731ce9ffdeeb204";
		// sqkxmi

		String pass9Hash = "16086b2db45d028d8da063ab48115cf817915717b528d8c149103f74593d18b7";
		// uocgu

		String pass10Hash = "02456ae1f45b9e141a0550d5eab837c125bbb0eeab6f8a67abef6f3862c5b695";
		// irkyql

		// Our brute variables
//		String pass4 = "aaaa";
//		String pass5 = "aaaaa";
//		String pass6 = "aaaaaa";
//		String pass7 = "aaaaaaa";
		
		// Our CharMath variables
//		int a = 0;
//		int b = 0;
//		int c = 0;
//		int d = 0;
//		int e = 0;
//		int f = 0;
//		int g = 0;

		// Four-Letter Passwords

//		int a = pass4.charAt(0);
//		int b = pass4.charAt(1);
//		int c = pass4.charAt(2);
//		int d = pass4.charAt(3);
//
//		while (a <= 'z') {
//
//			while (b <= 'z') {
//
//				while (c <= 'z') {
//
//					while (d <= 'z') {
//						String passGuess = foundSalt + String.valueOf((char) a
//								+ String.valueOf((char) b + String.valueOf((char) c + String.valueOf((char) d))));
//						d++;
//
//
////						System.out.println(passGuess);
////						System.out.println(hashedHex(SHAzam(passGuess)));
////
//						if (hashedHex(SHAzam(passGuess)).equals(pass1Hash)) {
//							System.out.println("Pass 1 is " + passGuess);
//						} else if (hashedHex(SHAzam(passGuess)).equals(pass2Hash)) {
//							System.out.println("Pass 2 is " + passGuess);
//						} else if (hashedHex(SHAzam(passGuess)).equals(pass3Hash)) {
//							System.out.println("Pass 3 is " + passGuess);
//						} else if (hashedHex(SHAzam(passGuess)).equals(pass4Hash)) {
//							System.out.println("Pass 4 is " + passGuess);
//						} else if (hashedHex(SHAzam(passGuess)).equals(pass5Hash)) {
//							System.out.println("Pass 5 is " + passGuess);
//						} else if (hashedHex(SHAzam(passGuess)).equals(pass6Hash)) {
//							System.out.println("Pass 6 is " + passGuess);
//						} else if (hashedHex(SHAzam(passGuess)).equals(pass7Hash)) {
//							System.out.println("Pass 7 is " + passGuess);
//						} else if (hashedHex(SHAzam(passGuess)).equals(pass8Hash)) {
//							System.out.println("Pass 8 is " + passGuess);
//						} else if (hashedHex(SHAzam(passGuess)).equals(pass9Hash)) {
//							System.out.println("Pass 9 is " + passGuess);
//						} else if (hashedHex(SHAzam(passGuess)).equals(pass10Hash)) {
//							System.out.println("Pass 10 is " + passGuess);
//						}
////						 Test
////						System.out.println(passGuess);
//					}
//					d = 'a';
//					c++;
//
//
//				}
//				c = 'a';
//				b++;
//
//			}
//			b = 'a';
//			a++;
//			
//			System.out.println("Four Letters; A is now " + (char)a);
//
//		}

		// Five-Letter Passwords

//		int a = pass5.charAt(0);
//		int b = pass5.charAt(1);
//		int c = pass5.charAt(2);
//		int d = pass5.charAt(3);
//		int e = pass5.charAt(4);
//
//		while (a <= 'z') {
//
//			while (b <= 'z') {
//
//				while (c <= 'z') {
//
//					while (d <= 'z') {
//
//						while (e <= 'z') {
//							String passGuess = foundSalt + String.valueOf((char) a + String.valueOf((char) b
//									+ String.valueOf((char) c + String.valueOf((char) d + String.valueOf((char) e)))));
//							e++;
//
//							if (hashedHex(SHAzam(passGuess)).equals(pass10Hash)) {
//								System.out.println("Code 10 is " + passGuess);
//							} else if (hashedHex(SHAzam(passGuess)).equals(pass1Hash)) {
//								System.out.println("Code 1 is " + passGuess);
//							} else if (hashedHex(SHAzam(passGuess)).equals(pass2Hash)) {
//								System.out.println("Code 2 is " + passGuess);
//							} else if (hashedHex(SHAzam(passGuess)).equals(pass3Hash)) {
//								System.out.println("Code 3 is " + passGuess);
//							} else if (hashedHex(SHAzam(passGuess)).equals(pass4Hash)) {
//								System.out.println("Code 4 is " + passGuess);
//							} else if (hashedHex(SHAzam(passGuess)).equals(pass5Hash)) {
//								System.out.println("Code 5 is " + passGuess);
//							} else if (hashedHex(SHAzam(passGuess)).equals(pass6Hash)) {
//								System.out.println("Code 6 is " + passGuess);
//							} else if (hashedHex(SHAzam(passGuess)).equals(pass7Hash)) {
//								System.out.println("Code 7 is " + passGuess);
//							} else if (hashedHex(SHAzam(passGuess)).equals(pass8Hash)) {
//								System.out.println("Code 8 is " + passGuess);
//							} else if (hashedHex(SHAzam(passGuess)).equals(pass9Hash)) {
//								System.out.println("Code 9 is " + passGuess);
//							}
//
//						}
//
//						e = 'a';
//						d++;
//
//					}
//
//					d = 'a';
//					c++;
//
//				}
//
//				c = 'a';
//				b++;
//
//			}
//
//			System.out.println("Five characters, A is currently " + (char) a);
//			b = 'a';
//			a++;
//
//		}
//
//		System.out.println("Once again. Done.");

		// Six-Letter Passwords

		int a = 'a';
		int b = 'a';
		int c = 'a';
		int d = 'a';
		int e = 'a';
		int f = 'a';

		while (a <= 'z') {

			while (b <= 'z') {

				while (c <= 'z') {

					while (d <= 'z') {

						while (e <= 'z') {

							while (f <= 'z') {

								String passGuess = foundSalt + String.valueOf(
										(char) a + String.valueOf((char) b + String.valueOf((char) c + String.valueOf(
												(char) d + String.valueOf((char) e + String.valueOf((char) f))))));
								f++;

								if (hashedHex(SHAzam(passGuess)).equals(pass10Hash)) {
									System.out.println("Code 10 is " + passGuess);
								} else if (hashedHex(SHAzam(passGuess)).equals(pass1Hash)) {
									System.out.println("Code 1 is " + passGuess);
								} else if (hashedHex(SHAzam(passGuess)).equals(pass2Hash)) {
									System.out.println("Code 2 is " + passGuess);
								} else if (hashedHex(SHAzam(passGuess)).equals(pass3Hash)) {
									System.out.println("Code 3 is " + passGuess);
								} else if (hashedHex(SHAzam(passGuess)).equals(pass4Hash)) {
									System.out.println("Code 4 is " + passGuess);
								} else if (hashedHex(SHAzam(passGuess)).equals(pass5Hash)) {
									System.out.println("Code 5 is " + passGuess);
								} else if (hashedHex(SHAzam(passGuess)).equals(pass6Hash)) {
									System.out.println("Code 6 is " + passGuess);
								} else if (hashedHex(SHAzam(passGuess)).equals(pass7Hash)) {
									System.out.println("Code 7 is " + passGuess);
								} else if (hashedHex(SHAzam(passGuess)).equals(pass8Hash)) {
									System.out.println("Code 8 is " + passGuess);
								} else if (hashedHex(SHAzam(passGuess)).equals(pass9Hash)) {
									System.out.println("Code 9 is " + passGuess);
								}

							}
							f = 'a';
							e++;

						}
						e = 'a';
						d++;

					}
					d = 'a';
					c++;

					/*
					 * We need a progress bar of SOME kind, so this is me implementing something I
					 * accidentally wasted a day because I was unable to read whether or not the
					 * program doing anything. Learnin' lessons!
					 */

					// Not this one, though; c is too early in the sequence and makes for too much
					// output
//					System.out.println("C is now " + (char) c);

				}
				c = 'a';
				b++;

				System.out.println("B is now " + (char)b);

			}
			b = 'a';
			a++;

			// If this increments, that means we're getting through everything
			System.out.println("Six characters, A is now " + (char)a);

		}
//		System.out.println("That it; trials done.");
	

	// Man, Passion Pit is a really good band. I've had "Lifted Up" stuck in my head
	// all day
	// "1985 was a good year...."
		
		// Naked hash (no salt) done. No dice	
		
		
	// 7-Letter Passwords
		// THERE AREN'T ANY :X

//		int a = 'z';
//		int b = 'z';
//		int c = 'z';
//		int d = 'z';
//		int e = 'z';
//		int f = 'z';
//		int g = 'z';
////		
//		boolean breaker = false;
//		
//		String breakerTest = "$@1tzxctvyb";
//		
//		String breakerTestHash = "64577c9a3969a17ec20602ba62be9a18899b2fbbd82bb0bdfd9b0ba69f15c98f";
//		
//		
//		
//		while (a >= 'a') {
//			
//			while (b >= 'a') {
//				
//				while (c >= 'a') {
//					
//					while (d >= 'a') {
//						
//						while (e >= 'a') {
//							
//							while (f >= 'a') {
//								
//								while (g >= 'a') {
//									
//									String passGuess = (foundSalt + String.valueOf((char) a + String.valueOf((char) b 
//											+ String.valueOf((char) c + String.valueOf((char) d + String.valueOf((char) e
//													+ String.valueOf((char) f + String.valueOf((char) g))))))));
//									
//									String hashGuess = hashedHex(SHAzam(passGuess));
////									
////									System.out.println(passGuess);
////									
//									if (hashGuess.equals(pass10Hash)) {
//										System.out.println("Password 10 is " + passGuess);
//										breaker = true;
//									} 
//									
//									// Breaker test
////									if (hashGuess.equals(breakerTestHash)) {
////										System.out.println("The breaker test was the following string:");
////										System.out.println(breakerTest);
////										System.out.println("Which hashes to");
////										System.out.println(breakerTestHash + "\n");
////										System.out.println("The current passGuess is");
////										System.out.println(passGuess);
////										System.out.println("Which hashes to");
////										System.out.println(hashGuess + "\n");
////										System.out.println("Therefore, breaker engaged. The next line should say \"Done.\"");
////										breaker = true;
////									}
//									
//									if (breaker)
//										break;
//									
//									g--;
//									
//								}
//								
//								if (breaker)
//									break;
//								
//								g = 'z';
//								f--;
//								
//							}
//							
//							if (breaker)
//								break;
//							
//							f = 'z';
//							e--;
//							
//						}
//						
//						if (breaker)
//							break;
//						
//						e = 'z';
//						d--;
//						
//					}
//					
//					if (breaker)
//						break;
//					
//					d = 'z';
//					c--;
//					
//				}
//				
//				if (breaker)
//					break;
//				
//				System.out.println("B is currently " + (char) b + ". Decrementing to " + (char)(b - 1) + ".");
//				
//				c = 'z';
//				b--;
//				
//			}
//			
//			if (breaker)
//				break;
//			
//			System.out.println ("A is currently " + (char) a + ". Decrementing to " + (char)(a - 1) + ".");
//			
//			b = 'z';
//			a--;	
//		}
		
//		System.out.println("End.");
		
	
	// DICTIONARY ATTACK
		
//	File dictionary = new File("C:\\Users\\Owner\\Desktop\\School\\CS-230\\Cracker Challenge\\words.txt");
//	try {
//		Scanner wordUp = new Scanner(dictionary);
//		
//		while (wordUp.hasNextLine()) {
//			String passGuess = foundSalt + wordUp.nextLine().toLowerCase();
//			
//			// Test
////			System.out.println(passGuess);
//			
//			String hashGuess = hashedHex(SHAzam(passGuess));
//			
//			// I want to include all 10 passwords, because if this dictionary
//			// attack works, it will re-ping the passwords I've already found.
//			if (hashGuess.equals(pass1Hash)) {
//			System.out.println("Password 1 is " + passGuess);
//		}
//		
//		else if (hashGuess.equals(pass2Hash)) {
//			System.out.println("Password 2 is " + passGuess);
//		} 
//		
//		else if (hashGuess.equals(pass3Hash)) {
//			System.out.println("Password 3 is " + passGuess);
//		} 
//		
//		else if (hashGuess.equals(pass4Hash)) {
//			System.out.println("Password 4 is " + passGuess);
//		} 
//		
//		else if (hashGuess.equals(pass5Hash)) {
//			System.out.println("Password 5 is " + passGuess);
//		} 
//		
//		else if (hashGuess.equals(pass6Hash)) {
//			System.out.println("Password 6 is " + passGuess);
//		} 
//		
//		else if (hashGuess.equals(pass7Hash)) {
//			System.out.println("Password 7 is " + passGuess);
//		} 
//		
//		else if (hashGuess.equals(pass8Hash)) {
//			System.out.println("Password 8 is " + passGuess);
//		} 
//		
//		else if (hashGuess.equals(pass9Hash)) {
//			System.out.println("Password 9 is " + passGuess);
//		} 
//		
//		else if (hashGuess.equals(pass10Hash)) {
//			System.out.println("Password 10 is " + passGuess);
//		} 
//			
//		}
// 		
//		wordUp.close();
//		
//	} 
//	
//	catch (FileNotFoundException e) {
//		System.out.println("Missing dictionary file; \"words.txt\".");
//		e.printStackTrace();
//	}
		
	System.out.println("Done.");
	
	// Leave this bracket in; it's the end of the driver class
}

//		// Testing "Sequence Breaking" Conditionals
//		String erBell = "!";
//		int e = erBell.charAt(0);
//
//		while (e <= 'z') {
//			String strungerBell = String.valueOf((char) e);
//			e++;
//			
//			System.out.println(strungerBell);
//
//			if (e == '+') {
//				e = '0';
//			} else if (e == ':') {
//				e = '@';
//			} else if (e == 'A') {
//				e = '^';
//			} else if (e == '_') {
//				e = 'a';
//			}
//		}

	/*
	 * Confirmed; this sequence printed out !, #, $, %, &, (, ), *, @, ^, 0-9, and
	 * a-z. Meaning, we should be able to implement these sequence breaks into every
	 * element of the loop, and that should cut us way down. 
	 */

//	}

	// This is the function that converts our guesses into SHA-256 code
	public static byte[] SHAzam(String input) throws NoSuchAlgorithmException {
		MessageDigest cooker = MessageDigest.getInstance("SHA-256");

		return cooker.digest(input.getBytes());
	}

	// This function takes the byte form of our digested guess and converts it into
	// a proper string
	public static String hashedHex(byte[] hash) {

		// I'll be the first to admit, I don't FULLY understand what the BigInteger
		// thing does; I came across it
		// while I was learning how to SHA in Java
		BigInteger stepOne = new BigInteger(1, hash);

		StringBuilder hashSlinger = new StringBuilder(stepOne.toString(16));

		// This pads insufficiently long hashes, but I don't think that'll ever be
		// necessary.
		while (hashSlinger.length() < 64) {
			hashSlinger.insert(0, '0');
		}

		return hashSlinger.toString();
	}
}