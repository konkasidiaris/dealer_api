-- :name drugs :? :*  ;; keyword, specifies the name of the function | :? :* signify that this query will return a list of results
-- :doc Get all drugs ;; optional documentation
SELECT * FROM dealer_dev.drugs;  -- the actual query

-- :name new-drug :insert :1
INSERT INTO
dealer_dev.drugs(name, availability, price)
-- ;; name availability and price in values signify variables
-- ;; they are provided by the caller in a map passed as second argument
VALUES(:name, :availability, :price)
RETURNING id;