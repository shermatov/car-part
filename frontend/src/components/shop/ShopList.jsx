import ShopItem from "./ShopItem.jsx";

export default function ShopList({ shops }) {
    if (!shops.length)
        return <p className="text-center text-gray-500 py-10">No shops available.</p>;

    return (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
            {shops.map((shop) => (
                <ShopItem key={shop.id} shop={shop} />
            ))}
        </div>
    );
}
