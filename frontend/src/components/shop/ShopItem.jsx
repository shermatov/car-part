import { useNavigate } from "react-router-dom";

export default function ShopItem({ shop }) {
    const navigate = useNavigate();

    return (
        <div
            className="bg-white rounded-xl shadow-sm p-5 cursor-pointer transition-all duration-200 hover:shadow-md hover:-translate-y-1"
            onClick={() => navigate(`/shops/${shop.id}`)}
        >
            <h3 className="text-xl font-semibold mb-2">{shop.name}</h3>
            <p className="text-gray-600 text-sm mb-3">
                {shop.description || "No description available"}
            </p>
            <div className="flex flex-wrap gap-2 text-xs">
                <span className="bg-gray-100 rounded px-2 py-1">{shop.address}</span>
                <span className="bg-gray-100 rounded px-2 py-1">{shop.phone}</span>
            </div>
        </div>
    );
}
