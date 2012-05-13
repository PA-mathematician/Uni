f :: Int -> String -> Int
f 0 _ = 77
f x y = f (x - 1 + length y) (tail y)

g :: Int -> String -> Int
g _ "" = 88
g 0 _ = 77
g x y = g (x - 1 + length y) (tail y ++ tail y)

h :: Int -> String -> Int
h _ "" = 88
h 0 _ = 77
h x y = h (x - 1 + length y) (tail y ++ tail y)

t :: Int -> Int -> Int
t 0 0 = 0
t 0 x = t 0 (x - (x `div` 2))
t x y = y * (t (x + (x `div` 2) - 2) (y - y`div` 2))