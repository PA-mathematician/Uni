---------------------------------------------
-- Aufgabe 0.1: Funktionale Programmierung --
---------------------------------------------

data Tree = 
	Value Integer
	| Mark Op Tree Tree
	deriving (Eq, Show)

data Op = Plus | Mult | Minus
	deriving (Eq, Show)


-- Beispiel Bäume:
example_1a = (Mark Plus (Value 3) (Mark Minus (Value 0) (Value (-4)))) :: Tree
example_1b = (Mark Plus (Value 3) (Mark Minus (Value 2) (Value (-4)))) :: Tree


-- Lösung von David
balanced :: Tree -> Bool
balanced x
    | count (x) == 0  = True
    | otherwise       = False

count :: Tree -> Integer
count (Value x)
    | x < 0 = -1
    | x > 0 = 1
    | otherwise = 0
count (Mark x t1 t2) = (count t1) + (count t2)


---------------------------------------------
-- Aufgabe 0.2: Funktionale Programmierung --
---------------------------------------------

example_2a = [[], [1,2], [2]]
example_2b = 4

laenge :: [a] -> Integer
laenge [] = 0
laenge (x:xs) = 1 + laenge xs

summe :: Integer -> Integer
summe x | x <= 0 = 0 
        | otherwise = x + summe (x - 1)

-- Higher-Order-Lösung von Markus
{-
laenge :: [a] -> Integer
laenge = foldl (\i _ -> i+1) 0

summe :: Integer -> Integer
summe i = foldl (+) 0 [0..i]
-}

summe' :: [Integer] -> Integer -> Integer
summe' [] _ = 0
summe' _ 0 = 0
summe' (x:xs) n = x + summe' xs (n-1)


---------------------------------------------
-- Aufgabe 0.3: Funktionale Programmierung --
---------------------------------------------

inStunden :: Zeit -> Integer
inStunden (WTS w t s) = w * 168 + t * 24 + s

alsZeit :: Integer -> Zeit
alsZeit x = WTS (x `div` 168) ((x `mod` 168) `div` 24) ((x `mod` 169) `mod` 24)

-- Die rekursive Varianten
inStunden' :: Zeit -> Integer
inStunden' (WTS 0 0 s) = s
inStunden' (WTS 0 t s) = inStunden (WTS 0 0 (s + 24 * t))
inStunden' (WTS w t s) = inStunden (WTS 0 (t + 7 * w) s)


alsZeit' :: Integer -> Zeit
alsZeit' x = alsZeitR x (WTS 0 0 0)

-- Hilfsfunktion
alsZeitR :: Integer -> Zeit -> Zeit
alsZeitR 0 z = z
alsZeitR x (WTS w t s)
       | x >= 168  = alsZeitR (x - 168) (WTS (w + 1) t s) -- div 168
	   | x >= 24   = alsZeitR (x - 24) (WTS w (t + 1) s)  -- div 24
	   | otherwise = WTS w t x
