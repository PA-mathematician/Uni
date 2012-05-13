data FracTree = FracNode FracTree FracTree Integer Integer 
              | FracLeaf Integer Integer


countN :: FracTree -> Integer
countN (FracLeaf a b) = if (a `mod` b == 0)
                        then 1
                        else 0
countN (FracNode l r a b) = if (a `mod` b == 0)
                            then 1 + countN(l) + countN(r)
                            else countN(l) + countN(r)

-- countN (FracNode (FracLeaf 2 5) (FracLeaf 2 1) 3 8) == 1
-- countN (FracNode (FracLeaf 12 3) (FracLeaf 14 7) 2 4) == 2


infty :: FracTree -> Bool
infty (FracLeaf a b) = if (b == 0) then True else False
infty (FracNode l r a b) = if (b == 0)
                            then True
                            else infty(l) || infty(r)

-- infty (FracNode (FracLeaf 2 5) (FracLeaf 2 1) 3 8) == False
-- infty (FracNode (FracLeaf 2 5) (FracLeaf 2 0) 3 8) == True